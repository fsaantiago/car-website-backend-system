## Eureka Server

Eureka Server is an application that holds the information about all client-service applications. Every Micro service will register into the Eureka server and Eureka server knows all the client applications running on each port and IP address.

<img src="/images/eureka-server.png">

### Details

- __EurekaServerApplication__: The main class that runs the Eureka Server application. It has `@EnableEurekaServer` to enable it as Eureka Server.

- __resoures\applications.properties__: It has below properties for Eureka server:
````propierties
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
logging.level.com.netflix.eureka=ON
logging.level.com.netflix.discovery=ON
````

### Run the application

Below are the commands to build and run this project.
```
$ mvn clean package
```

```
$ java -jar target/eurekaServer-0.0.1-SNAPSHOT.jar
```


You can also import it as a Maven project on your preferred IDE and run the class `EurekaServerApplication`.

