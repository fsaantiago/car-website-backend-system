### Code Overview: Location Service (Boogle Maps)

Within the `boogle-maps` folder, you'll discover the code pertinent to location service. This service functions as a Mock to replicate a Maps WebService. It operates on the premise that, when provided with a latitude and longitude, it will furnish a randomized address.

While you're not required to enact any implementation as part of this application, let's briefly examine the files included. It's worth noting that each package resides within `com.udacity`; hence, we'll omit that portion of the package name in the descriptions below.

<img src="/images/boogle-map.png">

## Boogle Maps

`boogle.maps`

#### Address:

This class defines the Address entity, primarily comprising private variables such as address, city, state, and zip code. Notably, latitude and longitude are not stored here; they are obtained from the Vehicles API.

#### BoogleMapsApplication:

This class initializes Boogle Maps as a Spring Boot application.

#### MapsController:

This serves as the actual REST controller for the application. It handles `GET` requests and responds accordingly. In our scenario, being a Mock of a WebService, it simply returns a random address from the repository.

#### MockAddressRepository:

Repositories typically offer data persistence while the web service is operational. In this instance, this Mock repository selects a random address from the `ADDRESSES` array defined in the file.



