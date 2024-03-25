# theScore QA Automation Mobile Challenge

This repository contains the mobile automation framework to automate and 
test components of theScore's media native Android app. The project is
written in Java using the [Selenium](https://www.selenium.dev/) and 
[Appium](https://appium.io/docs/en/latest/) framework and using [TestNG](https://testng.org/) testing
framework. [Maven](https://maven.apache.org/) is used for build automation and for 
project tool management. The framework uses the Page Object Model design pattern. 

Click on the links below to learn more about the framework structure, tech 
stack and setup/run guide.

## Table of Contents

1. [Prerequisites](#prerequisites)
   1. [Other Prerequisites](#other-prerequisites)
2. [Setup](#setup)
3. [Running Tests](#running-tests)
   1. [Running All Tests](#running-all-tests)
   2. [Running Single Test](#running-tests-from-test-class)
4. [Project Structure](#project-structure)
   1. [app](#app)
   2. [Page Objects](#srctestjavapageobjects)
   3. [Tests](#srctestjavatests)
5. [Reporting](#reporting)
6. [Test Specification](#test-specification)
   1. [Test Data](#test-data)
7. [Rationale & Coverage Assessment](#rationale--coverage-assessment)
   1. [Design Patter](#design-pattern)
   2. [BaseTest](#basetest)

## Prerequisites

| Software                   | Version |
|----------------------------|---------|
| Java Development Kit (JDK) | 22      |
| Appium                     | 2.5.1   |
| Node                       | 18.14.0 |
| Maven                      | 3.9.6   |

#### Other Prerequisites

* Android emulator (Pixel 7 API 34)

## Setup

1. Clone the repository and in a terminal, navigate to the project directory
2. In a separate terminal window, start the appium server by running: `appium`
3. Launch the Android emulator (Pixel 7 API 34). Install the devices/API if not already installed
4. Install dependencies using maven: `mvn install -DskipTests`

## Running Tests

Ensure setup steps are complete - Appium server should be running and the right 
version of the Android emulator is running. 

#### Running All Tests
```mvn test```

#### Running Tests by Class
```mvn test -Dtest=TestClassName```

## Project Structure

#### app

The app folder contains the application under test. The tests use theScore 
Android app (apk) downloaded from https://apkpure.com/. The app version is 
24.4.0.

#### src/test/java/pageobjects

The `pageobjects` folder contains the page objects used when writing tests. The
pages are further organized in different packages depending on the functional area
of the application they cover. A page object contains the elements and actions
that can be performed on any page in the application. An instance of page would 
then be created in a test and these actions would then be called.

All page objects inherit from a `BasePage` which contains the base application 
interactions such as `click`, `getText` and so on. Each interaction is only
called after an appropriate `WebDriverWait` is performed. This is to ensure that 
the elements load and are ready to be interacted with before an action is performed.
Doing this improves the stability of the tests.

#### src/test/java/tests

The tests for mobile application are stored here. The tests use the TestNG testing framework.
All tests inherit from `BaseTest` which contains a setup method that runs before 
each test. The setup method installs the application under test and prepares 
the app to be in a ready state to run.

## Reporting

Allure is used for test reporting. On completion of a test run, the 
`allure-results` folder is generated. To generate and view the report, 
run the following commands:

```
allure generate --clean
allure open
```

## Test Specification

The test steps for each test can be found in their respective classes [LeagueSelectionTest](https://github.com/mmenezes07/the-score-mobile-tests/blob/5e674516ddec3765e5521006baa35cfd271de2c6/src/test/java/tests/LeagueSelectionTest.java) 
and [TeamSelectionTest](https://github.com/mmenezes07/the-score-mobile-tests/blob/5e674516ddec3765e5521006baa35cfd271de2c6/src/test/java/tests/TeamSelectionTest.java).

The tests use the [Page Object Model design pattern](#design-pattern). The page
objects are initialized in the tests and the methods are called from there. The 
implementation of each method is abstracted by the page and this makes the tests
cleaner and easier to read. It also makes the tests easier to maintain since in 
most cases, only the method or properties within a page need to be updated and the
tests would remain untouched.

#### Test Data

Both the `LeagueSelectionTest` and `TeamSelectionTest` are parameterized and can be used
to test most leagues/teams. There is an assertion included that tests the names of the
teams in the standings and another one checking the team stats. This check
ensures that the right league and right team's stats are displayed.

However, some of the test data may cause the tests to fail
and these assertions are commented out for now. For the league test, the teams in
the playoff picture could change, causing the test to fail. For the team stats test,
the stats are changing every day and so the assertion could fail.

Ideally, we would have a staging or test environment where we could specify the test
data for a league/team including the team names, standings, stats and so on.
We could then use that test data to assert the playoff picture/stadings/stats
accurately since we know the right standings. Alternatively, if we had access to
the API used by this page, we could get the API data and assert the data accurately
that way.

## Rationale & Coverage Assessment

#### Design Pattern

The automation framework uses the Page Object Model design pattern.
The benefit of using this
design pattern is that it separates the application logic from the test
implementation. This promotes maintainability, reusability, and scalability when writing tests.
If there are any changes to the locator on the application end, it is easy to
update the locator in a page object. The change would only need to be made in
a single place without the need to update any tests. Additionally, tests are
easier to read and are much better organized.

#### BaseTest

The base test houses the setup and tearDown methods which are run before/after each 
class. The idea is to reset the app to a clean state before running the next test.
If a test fails for any reason, this reset will ensure that the next test does not
start from that failed state and potentially fail as a result. This helps preserve
test independence.

The appium desired capability `no reset` is set to true to retain app user data.
This enables the app to open directly on the home page rather than having to 
navigate through the welcome screen as a user would if they were opening the app for the
first time. This saves a significant amount of time when running tests.
There is code in place to check if this welcome page is displayed and 
the code handles the navigation through these pages if necessary.