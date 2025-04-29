FROM maven:3.8.4-openjdk-17-slim AS builder

# Install dependencies and browsers
RUN apt-get update && apt-get install -y \
    wget curl unzip gnupg xz-utils jq \
    libgtk-3-0 libdbus-glib-1-2 libasound2 libx11-xcb1 \
    libxcomposite1 libxcursor1 libxdamage1 \
    libnss3 libxrandr2 \
    libxss1 libgbm1 && \
    rm -rf /var/lib/apt/lists/*

# Firefox
RUN FIREFOX_URL=$(curl -s -L -w "%{url_effective}" -o /dev/null \
    "https://download.mozilla.org/?product=firefox-latest&os=linux64&lang=en-US") && \
    wget -q "$FIREFOX_URL" -O /tmp/firefox.tar.xz && \
    tar -xf /tmp/firefox.tar.xz -C /opt/ && \
    ln -sf /opt/firefox/firefox /usr/bin/firefox && \
    rm /tmp/firefox.tar.xz

# GeckoDriver
RUN GECKODRIVER_VERSION=$(curl -s https://api.github.com/repos/mozilla/geckodriver/releases/latest | jq -r .tag_name | sed 's/^v//') && \
    wget -q "https://github.com/mozilla/geckodriver/releases/download/v$GECKODRIVER_VERSION/geckodriver-v$GECKODRIVER_VERSION-linux64.tar.gz" -O /tmp/geckodriver.tar.gz && \
    tar -xzf /tmp/geckodriver.tar.gz -C /usr/local/bin && \
    chmod +x /usr/local/bin/geckodriver && \
    rm /tmp/geckodriver.tar.gz

# Chrome
RUN curl -fsSL https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /etc/apt/trusted.gpg.d/google.gpg && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# ChromeDriver
RUN CHROME_VERSION=$(google-chrome-stable --version | awk '{print $3}') && \
    MAJOR_VERSION=$(echo "$CHROME_VERSION" | cut -d '.' -f 1) && \
    CHROMEDRIVER_URL=$(curl -s https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions-with-downloads.json \
        | jq -r --arg ver "$MAJOR_VERSION" '.channels.Stable.downloads.chromedriver[] | select(.platform == "linux64") | .url') && \
    wget -q "$CHROMEDRIVER_URL" -O /tmp/chromedriver.zip && \
    unzip /tmp/chromedriver.zip -d /tmp/chromedriver && \
    mv /tmp/chromedriver/chromedriver-linux64/chromedriver /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver && \
    rm -rf /tmp/chromedriver /tmp/chromedriver.zip

WORKDIR /app
COPY . .
RUN mvn clean install

FROM maven:3.8.4-openjdk-17-slim

RUN apt-get update && apt-get install -y \
    libgtk-3-0 libdbus-glib-1-2 libasound2 libx11-xcb1 \
    libxcomposite1 libxcursor1 libxdamage1 \
    libnss3 libxrandr2 \
    libxss1 libgbm1 && \
    rm -rf /var/lib/apt/lists/*

COPY --from=builder /root/.m2 /root/.m2
COPY --from=builder /opt/firefox /opt/firefox
COPY --from=builder /usr/local/bin/geckodriver /usr/local/bin/geckodriver
COPY --from=builder /usr/local/bin/chromedriver /usr/local/bin/chromedriver
COPY --from=builder /usr/bin/google-chrome-stable /usr/bin/google-chrome-stable
COPY --from=builder /app /app
RUN ln -sf /opt/firefox/firefox /usr/bin/firefox

ENV DISPLAY=:99
WORKDIR /app

CMD ["mvn", "test"]
