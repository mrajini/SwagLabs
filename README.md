# **Swag Labs Automation Framework**

## Introduction`

This is an automated test framework for the Swag Labs E-commerce website (link to website). The framework is designed to validate key functionalities, including login, browsing products, adding items to the cart, and completing orders.

This framework uses Java, Selenium WebDriver, Cucumber, and TestNG with the Page Object Model (POM) design pattern to ensure clean, maintainable, and scalable test code.

## Technologies Used

Java - Programming language for writing tests
Selenium WebDriver - For browser automation
Cucumber - Behavior-Driven Development (BDD) tool, used to write Gherkin syntax feature files
TestNG - Testing framework for test execution and reporting
Maven - Build and dependency management tool
Page Object Model (POM) - Design pattern to organize code

## Framework Structure

|-- src
|   |-- main
|       |-- java
|           |-- pages     # Page Object classes for each web page
|           |-- utilities           # Utility classes like configuration, helper functions
|   |-- test
|       |-- java
|           |-- stepDefs # Step definitions for Cucumber scenarios
|           |-- testRunner         # Test runner classes for different suites
|           |-- resources
                |-- config
|               |-- features    # Cucumber feature files
|-- target                      # Folder for TestNG reports and logs
|-- pom.xml                       # Maven POM file for dependencies
|-- README.md                     # Project documentation`

## Clone the repository:
Clone the repo or Download the zip file
`cd swag-labs-automation`

#### Install dependencies: 
Run the following command to install dependencies:
`mvn clean install`

#### Configure properties: 
Update config.properties file located in the src/main/resources directory with necessary configurations like browser type, base URL, and timeout settings.

#### Running Tests

##### Running All Tests
To run all tests using the default TestNG runner:
`mvn test`

##### Running Specific Scenarios
To run specific scenarios, use the Cucumber tags. For example:
`mvn test -Dcucumber.options="--tags @Login"`

##### Running Test Suites
Regression Suite:
`mvn test -Dtest=RegressionTest`
Smoke Suite: 
`mvn test -Dtest=SmokeTest`

#### Browser Configuration
You can change the browser from the command line by specifying a system property:
`mvn test -Dbrowser=chrome`

#### Test Reports
##### Cucumber HTML Report
Cucumber generates a detailed report for each scenario, located in target/cucumber-reports.
