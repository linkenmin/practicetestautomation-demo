# practicetestautomation-demo

This project demonstrates browser-based UI testing using **Java**, **Cucumber**, and **Selenium WebDriver** for a practice login website: [https://practicetestautomation.com/practice-test-login/](https://practicetestautomation.com/practice-test-login/). It provides a clean, modular test framework for both **Chrome** and **Firefox** browsers using WebDriverManager.

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

## Contributing

Feel free to fork and make improvements! Any contributions are welcome.

## License

This project is open-source and available under the [MIT License](LICENSE).