# theScore QA Automation Mobile Challenge

This repository contains the a mobile automation framework to automate and 
test components of theScore's media native Android app. The project is
written in Java using the Selenium and Appium framework and using TestNG as 
the test runner. Maven is used for build automation and for the project 
management. The framework uses the Page Object Model design pattern. Click 
on the links below to learn further about the framework structure, tech 
stack and setup/run guide.

## Table of Contents

1. [Prerequisites](#prerequisites)
   1. [Other Prerequisites](#other-prerequisites)
2. [Setup](#setup)
3. [Running Tests](#running-tests)
4. [Project Structure](#project-structure)
5. [Reporting](#reporting)

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
2. In a separate terminal window, start the appium server
`appium`
3. Launch the Android emulator (Pixel 7 API 34). Install the devices/API if not already installed
4. Install dependencies using maven
`mvn install -DskipTests`

## Running Tests

Ensure setup steps are complete - Appium server should be running and the right 
version of the Android emulator is running. 

Run the following command to run all tests `mvn test`
Run the following command to run select tests `mvn test -Dtest=path.to.test`

## Project Structure

### app

The app folder contains the application under test. The tests use theScore 
Android app (apk) downloaded from https://apkpure.com/. The app version is 
24.4.0.

### src/test/java/pageobjects

The `pageobjects` folder contains the page objects used when writing tests. The
pages are further organized in different packages depending on the functional area
of the application they cover. A page object contains the elements and actions
that can be performed on any page in the application. An instance of page would 
then be created in a test and these actions would then be called.

All page objects inherit from a `BasePage` which contains the base application 
interactions such as `click`, `getText` and so on.

The benefit of using the Page Object Model
design pattern is that is separates the application logic from the test 
implementation. This promotes maintainability, reusability, and scalability when writing tests.
If there are any changes to the locator on the application end, it is easy to 
update the locator in a page object. The change would only need to be made in 
a single place without the need to update any tests. Additionally, tests are 
easier to read and are much better organized.

### src/test/java/tests

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
