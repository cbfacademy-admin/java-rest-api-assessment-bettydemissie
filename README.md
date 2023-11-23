# **Issue API 🚀**
<hr>
Welcome to the Issue API, designed to empower the Operations team at UBS by offering a seamless solution for managing, tracking, and assigning issues across the organization. This system simplifies the process of creating structured issues, providing the Operations team with a templated structure to efficiently assess and resolve a wide range of challenges.

## Purpose

The primary goal of this API is to facilitate the Operations team at UBS in their day-to-day activities by providing an intuitive and powerful tool. Whether it's handling tasks, incidents, or resolving errors, this Issues API offers a robust platform for effective issue management. The API stores and retrieves data using a JSON file, ensuring that the Operations team can effortlessly navigate through various issues, promoting organizational efficiency.

## Key Features

- **Structured Issues:**  Easily create issues with a templated structure for consistency and clarity.
- **Effortless Tracking:** Streamline the tracking process for tasks, incidents, and errors.
- **Assignment Capabilities:** Assign and manage issues seamlessly across the organization.
- **Organizational Insights:** Gain insights into ongoing issues to make informed decisions.
- **JSON Data Storage:** Utilize a JSON file for efficient and organized data storage.

This API is designed to enhance the workflow of the Operations team, providing them with a user-friendly and efficient solution for managing the dynamic landscape of organizational challenges.
## Key Dependencies Used

* **Springfox Swagger (springfox-boot-starter):**
* Purpose: Integrates Swagger with Spring Boot to generate API documentation.
* Usage: Automates the documentation of your APIs, making it easy to understand and test them.

* **Swagger Core (swagger-core):**
* Purpose: Core libraries for generating Swagger documentation.
* Usage: Works in conjunction with springfox for API documentation.

* **Gson (gson):**
* Purpose: A library for JSON serialization and deserialization.
* Usage: Used for handling JSON data in your application.
## Data Model

**Issue**
```java 

public class Issue {
private Long id;
private String title;
private String description;
private Status status;
private Employee assignedTo;
private Employee createdBy;
}

public enum Status {
PENDING, IN_PROGRESS, COMPLETE;
}
```
- id: Unique identifier for each issue.
- title: Concise title describing the issue.
- description: Detailed information about the issue.
- status: Current status of the issue (e.g., PENDING, IN_PROGRESS, COMPLETE).
- assignedTo: Employee assigned to handle the issue.
- createdBy: Employee who created the issue.

**Employee**
```java 
public class Employee {
private Long id;
private String firstName;
private String lastName;
private String email;
private Department department;
}

public enum Department {
GROUP_FUNCTIONS,
ASSET_MANAGEMENT,
INVESTMENT_BANKING,
WEALTH_MANAGEMENT,
PRIVATE_CORPORATE;
}
```
- id: Unique identifier for each employee.
- firstName: First name of the employee.
- lastName: Last name of the employee.
- email: Email address of the employee.
- department: Department in which the employee works (e.g., GROUP_FUNCTIONS, ASSET_MANAGEMENT, INVESTMENT_BANKING, WEALTH_MANAGEMENT, PRIVATE_CORPORATE).

Feel free to explore the API endpoints to create, retrieve, update, and delete issues, as well as manage employees involved in the resolution process.

## End Points

Here are all the endpoints to add to `htgittp://localhost:8081`:

** Issue **

Add a New Issue

Endpoint: POST /api/v1/issues/add
Description: Creates a new issue with the provided details.

Get All Issues

Endpoint: GET /api/v1/issues/all
Description: Retrieves a list of all issues.

Fetch Issue Details

Endpoint: GET /api/v1/issues/fetch/{issueId}
Description: Retrieves details of a specific issue based on its ID.

Update Issues Assigned to Employee

Endpoint: PUT /api/v1/issues/update/{issueId}
Description: Updates the assigned employee for a specific issue.

Update Issue Status

Endpoint: PUT /api/v1/issues/update/status/{issueId}
Description: Updates the status of a specific issue.

Delete an Issue

Endpoint: DELETE /api/v1/issues/void/{issueId}
Description: Deletes a specific issue based on its ID.

Get Issues by Status

Endpoint: GET /api/v1/issues/status/{status}
Description: Retrieves a list of issues based on their status.

Get Issues Assigned to an Employee

Endpoint: GET /api/v1/issues/{employeeId}
Description: Retrieves a list of issues assigned to a specific employee.

** Employee **

Get All Employees

Endpoint: GET /api/v1/employees/all
Description: Retrieves a list of all employees.

Get Employee by ID

Endpoint: GET /api/v1/employees/{employeeId}
Description: Retrieves details of a specific employee based on their ID.

Add a New Employee

Endpoint: POST /api/v1/employees/add
Description: Creates a new employee with the provided details.

## Documentation
While the application is running, you can explore the comprehensive documentation and operational endpoints at: 
[Issue API Swagger Documentation] http://localhost:8081/swagger-ui/index.html#/




[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/iDPpP-d0)
# **Java API Assessment**

## **Introduction**
Dive into the world of API development using Java and SpringBoot. We're handing over a skeleton codebase; your challenge is to shape a top-notch API from it.

You can build any API of your choosing, but it must include the following:

1. At least one algorithm
1. Unit test at least one class
1. Store the data in a JSON file 
1. Exception handling 
1. Evidence of inheritance
1. Good use of HTTP Protocols - methods, request and response, have full CRUD operations supported 
1. Documentation

### **Learning Outcomes:**

By the end of this assessment, you should be able to:

1. **Design and Architect APIs**: Get to grips with the nitty-gritty of curating a top-quality API, focusing on data flow and endpoint interactions.
1. **Implement Best Practices**: Showcase your adherence to Java & SpringBoot coding standards, error handling, and optimal project structure.
1. **Code Integration**: Seamlessly combine your creations with the provided skeleton codebase.
1. **Exception Management**: Efficiently handle exceptions, ensuring your API remains sturdy and dependable.

Onward with this assessment, you're set for a deep dive into API development with Java and SpringBoot.

## **Design & Requirements**

### **Design Considerations:**
- **API Flow**: Map out your API's progression, from endpoints to their functionalities.

### **Requirements List:**
- **Core**: Make use of Java and SpringBoot.
- **End Points**: Ensure they are detailed and fully operational.
- **Error Handling**: Your API should handle mishaps gracefully and return informative feedback.

### **Learning Outcomes:**
- Acknowledge the pivotal role of a focused design in APIs.
- See firsthand how a detailed requirements list can pave the way for successful development.

## **Repository Management**

- **Consistent Commits**: Commit often, capturing your progress and thought process.
- **README**: Not just an afterthought. Fill it with the essence of your API, setup instructions, and other salient details.

### **Learning Outcomes:**
- Hone your skills in effective version control.
- Recognise the value of a well-curated repository.

## **Code Quality & Structure**

- **Best Practices**: Stick to Java and SpringBoot best practices and conventions.
- **Modularity**: Your code should be modular, reusable, and easily comprehensible.

#### **Learning Outcomes:**
- Craft clean, efficient, and maintainable code.
- Harness Java and SpringBoot to the fullest.

---

## Getting Started

- [Prerequisites](#prerequisites)

- [Setup](#setup)

### Prerequisites

Before you begin, make sure you have the following installed:

1. [JDK 17](https://learn.microsoft.com/en-gb/java/openjdk/download#openjdk-17) (or higher)

2. [Git](https://git-scm.com/downloads)

3. [Visual Studio Code](https://code.visualstudio.com/Download)
   1. [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
   2. [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)

Also make sure you have accounts for the following:

1. [GitHub](https://github.com/signup)

### Setup

#### 1. Clone the Repository

```sh
git clone [REPO_URL]
cd [REPO_NAME]
```

Replace [REPO_URL] with the link to your GitHub repository and [REPO_NAME] with the repository's name.

#### 2. Install Dependencies

Open a terminal at the root of the repo directory and run the following command to install the dependencies:

```sh
./mvnw clean dependency:resolve
```

If you are on a Windows machine, that will be:
```cmd
mvnw clean dependency:resolve
```

You should see console output similar to the following:

```sh
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.cbfacademy:api-assessment >--------------------
[INFO] Building api-assessment 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ api-assessment ---
[INFO] Deleting /Users/user/Dev/cbfacademy/java-api-assessment/target
...
[truncated output]
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.060 s
[INFO] Finished at: 2023-10-03T16:18:25+01:00
[INFO] ------------------------------------------------------------------------
```

#### 3. Running the Application

To start the API in VS Code, press `F5` or tap the 'Play' icon for the `api-assessment` app in the Spring Boot Dashboard.

Alternatively, to start the API from the terminal, run the following command:

```sh
./mvnw spring-boot:run
```

Or on Windows:

```cmd
mvnw spring-boot:run
```

You should see console output similar to the following (press `Ctrl + C` to exit):

```sh
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------< com.cbfacademy:api-assessment >--------------------
[INFO] Building api-assessment 0.0.1-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ api-assessment ---
[INFO] Deleting /Users/gary/Dev/cbfacademy/java-api-assessment/target
[INFO] 
[INFO] >>> spring-boot:3.1.4:run (default-cli) > test-compile @ api-assessment >>>
[INFO] 
[INFO] --- resources:3.3.1:resources (default-resources) @ api-assessment ---
[INFO] Copying 1 resource from src/main/resources to target/classes
[INFO] Copying 0 resource from src/main/resources to target/classes
...
[truncated output]
...
2023-10-03T17:17:34.413+01:00  INFO 35536 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2023-10-03T17:17:34.751+01:00  INFO 35536 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-10-03T17:17:34.756+01:00  INFO 35536 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-10-03T17:17:34.756+01:00  INFO 35536 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.13]
2023-10-03T17:17:34.777+01:00  INFO 35536 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-10-03T17:17:34.778+01:00  INFO 35536 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 364 ms
2023-10-03T17:17:34.898+01:00  INFO 35536 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2023-10-03T17:17:34.907+01:00  INFO 35536 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-10-03T17:17:34.911+01:00  INFO 35536 --- [  restartedMain] com.cbfacademy.apiassessment.App         : Started App in 0.643 seconds (process running for 0.786)
```

Open your browser and navigate to `htgittp://localhost:8081`.

## **Deliverables**

Ensure that your work is merged to the `main` branch of your GitHub repository by the specified deadline (original or extended). Your solution will assessed based on its state *at that point*; any later commits will **not** be taken into account.

## FAQs

- Q: How can I process JSON in Java?
    
    A: There are a number of open-source packages that you can use to manipulate JSON. We recommend [Gson](https://github.com/google/gson), but you can also investigate alternatives like [json-simple](https://github.com/cliftonlabs/json-simple) or [Jackson](https://github.com/FasterXML/jackson-databind/).

- Q: Can I use another IDE I'm more familiar with instead of VS Code, like IntelliJ or Eclipse?

    A: You can if you wish, but only VS Code is formally supported by CBF Academy staff, so you do so at your own risk.

## Top Tips

- :camera_flash: Commit frequently and use meaningful commit messages. A granular, well-labelled history becomes an increasingly valuable asset over time.
- :cactus: Use feature branches. Build the habit of isolating your changes for specific tasks and merging them into your default branch when complete.
- :vertical_traffic_light: Use consistent naming conventions. Choose easily understandable names and naming patterns for your classes, functions and variables.
- :triangular_ruler: Keep your code tidy. Using the built-in formatting of VS Code or other IDEs makes your code easier to read and mistakes easier to spot.
- :books: Read the docs. Whether via Intellisense in your IDE, or browsing online documentation, build a clear understanding of the libraries your code leverages.
- :calendar: Don't wait until the last minute. Plan your work early and make the most of the time available to complete the assessment and avoid pre-deadline palpitations.
- :sos: Ask. :clap: For. :clap: Help! :clap: Your mentors, instructors and assistants are literally here to support you, so *make use of them* - don't sit and struggle in silence.

Best of luck! Remember, it's not just about the destination; it's the journey. Happy coding! 🚀