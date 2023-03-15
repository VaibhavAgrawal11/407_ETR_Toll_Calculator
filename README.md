# 407_ETR_Toll_Calculator

### This is a simple Java Spring Rest API project that calculates the cost of a vehicle driving from one point on 407ETR to another point. The toll rate used for this calculation is $0.25/km. The program uses a JSON file to read in the locations and routes between them.


## Requirements

To run this program, you will need:

- Java JDK 8 or later
- Maven 

## Getting Started

To get started with this program, follow these steps:

1. Clone this repository to your local machine.
2. Navigate to the project root directory.
'For this Projecct go to `{path}\407_ETR_Toll_Calculator-main\407_ETR_Toll_Calculator-main` 
3. Now, we don't need to install Maven, as a maven wrappper is already provided in a Springboot project, Just open a command prompt and run `mvnw install` and Maven project should be successfully built in your folder with all the dependencies installed.
- Make sure in you `jdk 1.8` or later version added in your `environment variables` otherwise the built will not be successfull.
- After the successfull built you should see `target` folder in your directory.
3. Open a terminal or command prompt and run the command `mvnw spring-boot:run`.
4. The program should start running on port 8080.
5. To kill the server press `Ctrl + C`.

## Usage

Once the program is running, you can make requests to the following endpoints:

### GET /loc
Returns the name and description of all locations.

### GET /toll
The body of the request should be a JSON object in the following format:
{
  "sourceId": 1,
  "destinationId": 5
}

Calculates the toll cost of driving from the location with id1 to the location with id2. 


## Custom Exceptions

This program uses custom exceptions implemented as enums to handle errors. The following exceptions are defined:

- `LocationNotFound`: Thrown when a entred location is invalid.
- `ROUTE_NOT_FOUND`: Thrown when a route is invalid, such as when the starting and destination locations are the same.
- `INVALID_LOCATION_IDS`: Thrown when a location with the given id is not found.

## Handling IO Exception

- The program uses a custom exception called `FILE_NOT_FOUND` to handle input/output exceptions that may occur when locating the JSON file.
- `INVAID_JSON_MAPPING` is custom exception thrown reading the locations from a JSON file. 
If an IO exception occurs, the program will return an error with a message indicating that an error occurred while reading the locations.

## Testing

This program includes JUnit tests for each of the methods. To run the tests, navigate to the project root directory and run the command `mvnw test`.

## Sample API

| API        | Method           |Input| Description
| ------------- |:-------------:|:-------------:| :-------------:| 
| http://localhost:8080/loc      | GET | | Returns the list of Locations from the JOSN file |
| http://localhost:8080/toll   | GET      | {"sourceId": 1,"destinationId": 5} | Return Toll Details and cost of Trip|

## Contributors
-Vaibhav Agrawal (https://www.linkedin.com/in/agrawalvaibh/)
