# TransLinkTest Automation Framework

This project aims to automate the process of searching for accurate bus information using the TransLink service. The primary functionalities include:

1. **Automated Bus Search:** The framework automates the process of searching for bus information, making it convenient for users to find relevant details about buses for a specified time range, such as from 7:00 am to 8:30 am on the next day.

2. **Selective Bus Information:** Users have the option to select specific bus information based on their preferences. They can easily filter the results by checking the appropriate box, making the process more streamlined.

3. **Favorites Management:** The project provides a "My Favorites" feature that allows users to add favorite bus routes. Users can also manage their favorite routes efficiently through this feature.

## Key Features

- Utilizes the **Page Object Model** design pattern to centralize and manage locators for each page.
  - Automation covers four distinct pages.
  - Methods relevant to each page are encapsulated within the respective page classes and are accompanied by explanations using command-line.

- **TestNG Dependency:** The codebase integrates the TestNG testing framework, ensuring organized and effective test execution.

- **Utils Package:** The framework incorporates a dedicated utils package to store essential browser utilities, configuration readers, and the Driver class.

- **Singleton Design Pattern:** The Driver class employs the Singleton design pattern, ensuring the use of a single browser session for multiple test cases, optimizing resource utilization.

# **IMPORTANT**
To enhance code comprehensibility, command-line explanations have been embedded while writing the code. These explanations provide insight into the functionality of various methods.

## Getting Started

To set up and use this automation framework, follow these steps:

1. Clone the repository to your local machine.
2. Install the necessary dependencies as outlined in the project.
3. Modify configuration files if required.
4. Run the test cases using the TestNG suite.
