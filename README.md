# Test Automation Framework for "chitatel.by"
This project is a comprehensive test automation suite designed to test both the UI and API of the website [chitatel.by](chitatel.by). The project is implemented using Java, leveraging Selenium for UI automation, RestAssured for API testing, and JUnit as the test framework. Allure Report is used for generating detailed test reports, and Jenkins is integrated for continuous integration and test execution.

## Key Features
- UI Testing: Automated testing of the website's front-end using Selenium WebDriver.
- API Testing: Validation of RESTful API endpoints using RestAssured.
- Test Framework: Organized test cases using JUnit for ease of maintenance and execution.
- Reporting: Allure Report for generating comprehensive and user-friendly test reports.
- CI/CD: Jenkins integration for running tests in a continuous integration pipeline.

## Reports
Test results and execution summaries are available in the Allure report after each run.

## Getting Started
1. Clone the repository.
2. Set up the necessary dependencies in your project (Java, Selenium, RestAssured, JUnit).
3. Configure Jenkins for continuous test execution.
4. Run tests on cpecified browser (chrome, firefox, edge, safari) using Maven command: "mvn clean test -Dbrowser=chrome"
5. Generate reports using Maven Allure command: "mvn allure:serve" or "mvn allure:report"

## CI/CD Integration
The project can be set up in Jenkins to automate tests after every code commit or on a scheduled basis.
