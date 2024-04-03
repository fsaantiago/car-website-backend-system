# Vehicles API

A REST API to maintain vehicle data and to provide a complete
view of vehicle details including price and address.

### VehiclesApiApplication
This initiates the Vehicles API as a Spring Boot application. Additionally, it sets up a few car manufacturers in the `ManufacturerRepository` and creates web clients to connect to the Maps and Pricing services.

<img src="/images/vehicle-api.jpeg">

`vehicles.api`

### API Error
Defines methods to quickly return errors and other messages from the Vehicles API.

### CarController
This is the actual REST controller for the application. It handles `GET`, `POST`, `PUT`, and `DELETE` requests (using methods in the `CarService`) and determines how they are responded to (including formatting with `CarResourceAssembler`). You will implement these methods in your code.

### CarResourceAssembler
This assists in mapping the `CarController` to the `Car` class to facilitate returning the API response.

### ErrorController
This aids in handling any invalid arguments provided to the API.

`vehicles.client.maps`

### Address
Similar to the `Address` file for boogle-maps, this defines a class for use with the `MapsClient`.

### MapsClient
Manages the format of a GET request to the `boogle-maps` WebClient to retrieve location data.

`vehicles.client.prices`

### Price
Similar to the `Price` file for `pricing-service`, this declares a class for use with the PriceClient.

### PriceClient
Handles the format of a GET request to the `pricing-service` WebClient to obtain pricing data.

`vehicles.domain`

### Condition
Lists the available values for the condition of a car (New oBoogle).

### Location
Declares information about the location of a vehicle. This differs from the Address class used by `boogle-maps`, primarily focusing on storing latitude and longitude values. As the data gathered from boogle-maps, such as `address`, is annotated as `@Transient`, this data is not stored until the next time `boogle-maps` is called.

`vehicles.domain.car`
### Car
Defines certain information about a given vehicle, mainly details about the car entry itself (such as `CreatedAt`). Note that a separate class, `Details`, also stores additional details about the car specific to make, color, and model. Similar to `Location` with data like address, this uses a `@Transient` tag with `price`, requiring a call to the Pricing Service whenever a price is desired.

### CarRepository
This repository provides data persistence while the web service is operational, primarily for vehicle information received in the `CarService`.

### Details
Specifies additional vehicle details, primarily related to the car build itself, such as `fuelType` and `mileage`.

`vehicles.domain.manufacturer`
### Manufacturer
Defines the Manufacturer class, primarily consisting of an ID code and manufacturer name.

### ManufacturerRepository
This repository provides data persistence while the web service is operational, primarily for storing manufacturer information like that initialized in `VehiclesApiApplication`.

`vehicles.domain`
### CarNotFoundException
Creates a `CarNotFoundException` that can be thrown when an issue arises in the `CarService`.

### CarService
The Car Service performs much of the code's work. It can retrieve either the entire list of vehicles or a single vehicle by ID (including calls to the maps and pricing web clients). It can also save updated vehicle information and delete an existing car. All of these actions are initiated by the CarController based on queries to the REST API. You will implement most of these methods yourself.

`test/../vehicles.api`
### CarControllerTest
Here, various methods performed by the CarController are tested by creating mock calls to the Vehicles API. You will implement some of these methods yourself for practice in building your own tests.

### Vehicles API Instructions
In this project, you will create a REST-based Vehicles API that communicates with a location and pricing service using Spring Boot. Additionally, you'll convert the existing Pricing Service API into a microservice registered on a Eureka server. Follow the [README and code instructions](https://github.com/fsaantiago/car-website-backend-system/blob/main/P02-VehiclesAPI/vehicles-api/README.md) and ensure you have completed all items as per the rubric.

### Convert the Pricing Service
Transform the Pricing Service into a microservice registered on a Eureka server. Also, add an additional test for the microservice.

### Test and Document the Vehicles API
Add tests for the CarController class.
Utilize Swagger to implement API documentation for the Vehicles API.

### Run the applications
Note that the Boogle Maps, Pricing Service, and Vehicles API applications must all be running for operations to perform correctly, although they can launch independently.

You can either use them in separate windows in your favorite IDE or use the following commands:

1. `$ mvn clean package` (run this in each directory containing the separate applications)

2. Boogle Maps:
```
$ java -jar target/boogle-maps-0.0.1-SNAPSHOT.jar
```

The service is available by defaultmvn port 9191. You can check it on the command line by using `$ curl http://localhost:9191/maps\?lat\=20.0\&lon\=30.0`

Pricing Service:
```
$ java -jar target/pricing-service-0.0.1-SNAPSHOT.jar
```

Vehicles API:
```
$ java -jar target/vehicles-api-0.0.1-SNAPSHOT.jar
```
When the Swagger API documentation is implemented, it should be available at: http://localhost:8080/swagger-ui.html

## Features

- REST API exploring the main HTTP verbs and features
- Hateoas
- Custom API Error handling using `ControllerAdvice`
- Swagger API docs
- HTTP WebClient
- MVC Test
- Automatic model mapping

## Instructions

#### Run the Code

To properly run this application you need to start the Orders API and
the Service API first.

```
$ mvn clean package
```

```
$ java -jar target/vehicles-api-0.0.1-SNAPSHOT.jar
```

Import it in your favorite IDE as a Maven Project.

## Operations

Swagger UI: http://localhost:8080/swagger-ui.html

### Create a Vehicle

`POST` `/cars`

```json
{
  "condition": "USED",
  "details": {
    "body": "sedan",
    "model": "Impala",
    "manufacturer": {
      "code": 101,
      "name": "Chevrolet"
    },
    "numberOfDoors": 4,
    "fuelType": "Gasoline",
    "engine": "3.6L V6",
    "mileage": 32280,
    "modelYear": 2018,
    "productionYear": 2018,
    "externalColor": "white"
  },
  "location": {
    "lat": 40.73061,
    "lon": -73.935242
  }
}
```

### Retrieve a Vehicle

`GET` `/cars/{id}`

This feature retrieves the Vehicle data from the database
and access the Pricing Service and Boogle Maps to enrich
the Vehicle information to be presented

### Update a Vehicle

`PUT` `/cars/{id}`

```json
{
  "condition": "USED",
  "details": {
    "body": "sedan",
    "model": "Impala",
    "manufacturer": {
      "code": 101,
      "name": "Chevrolet"
    },
    "numberOfDoors": 4,
    "fuelType": "Gasoline",
    "engine": "3.6L V6",
    "mileage": 32280,
    "modelYear": 2018,
    "productionYear": 2018,
    "externalColor": "white"
  },
  "location": {
    "lat": 40.73061,
    "lon": -73.935242
  }
}
```

### Delete a Vehicle

`DELETE` `/cars/{id}`


