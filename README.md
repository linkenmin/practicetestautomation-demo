# practicetestautomation-demo

This project demonstrates browser-based UI testing using **Java**, **Cucumber**, and **Selenium WebDriver** for a practice login website: https://practicetestautomation.com/practice-test-login/. It provides a clean, modular test framework for both **Chrome** and **Firefox** browsers using WebDriverManager.

## Design Philosophy

1. **Page Object Model (POM)**  
   The project uses the Page Object Model to encapsulate page-specific logic and elements. This makes the test code more maintainable and scalable as the application grows.

2. **Reusable Step Definitions**  
   Common login flows, assertions, and browser interactions are shared across scenarios to avoid duplication and promote modularity.

3. **Multi-Browser Support via Tags**  
   By using Cucumber tags (`@Chrome` and `@Firefox`), tests can be easily executed against different browsers with minimal setup.

4. **Clean Assertions with Messages**  
   Assertions include descriptive messages to make debugging easier when a test fails.

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/linkenmin/practicetestautomation-demo.git
   ```

2. **Install dependencies:**

   Ensure that you have **Maven** and **Java** installed.

   ```bash
   mvn install
   ```

## Run the Tests

1. **Run all tests:**

   This will run all login test scenarios for both browsers.

   ```bash
   mvn test
   ```

2. **Run tests by tag:**

   - Only run tests in Chrome:

     ```bash
     mvn test -Dcucumber.filter.tags="@Chrome"
     ```

   - Only run negative test cases:

     ```bash
     mvn test -Dcucumber.filter.tags="@NegativeTest"
     ```

## Docker Integration

This project includes a `Dockerfile` to easily build and run your tests inside a lightweight Docker container. It uses a **multi-stage build** based on **Maven 3.8.4 with OpenJDK 17 (slim variant)**.

If you're building on an **ARM64-based machine** (like Apple Silicon), it's recommended to use `buildx` with the `--platform=linux/amd64` option to create an **AMD64-compatible image**:

```
docker buildx build --platform=linux/amd64 ...
```

This ensures the final image is not only significantly smaller in size, but also compatible with cloud CI/CD environments like **AWS CodeBuild**, which often expect AMD64 images by default.

If you're already on an AMD64 machine, you can build the image directly without using `buildx`.

## Project Structure

```
src/
├── main/                                # Typically for production code, but this project focuses on tests
└── test/
    └── java/
        ├── features/
        │   ├── chrome.feature           # Login tests for Chrome (positive + negative)
        │   └── firefox.feature          # Login tests for Firefox (positive + negative)
        ├── pages/
        │   ├── LoginPage.java           # Page object for login page
        │   └── SecurePage.java          # Page object for secure area after login
        ├── runners/
        │   └── TestRunner.java          # Cucumber test runner class
        ├── steps/
        │   └── CommonSteps.java         # Step definitions shared across scenarios
        └── utils/
            ├── ChromeUtils.java         # ChromeDriver setup
            └── FirefoxUtils.java        # FirefoxDriver setup
```
