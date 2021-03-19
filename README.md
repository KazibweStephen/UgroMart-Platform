# Getting Started
### Description
UgroMart is a platform connecting customers to local market vendors. Users can sign up, view market vendors and purchase local produce like food, fruits and vegetables. UgroMart is an end to end solution that handles payments, deliveries, product and order management.

### Architecture
- The platform is built basing on a Hybrid or Rest and Event Driven Microservices architecture Using the following technologies
 - Spring Cloud, 
 - RabbitMq for Messaging, 
 - Docker for Containerization with docker-compose
 - Netflix Eureka for Service.
Much as Java/SpringBoot is used, the platform microservices should be technology agnostic since RabitMq is hosted separately
- The api is secured using JWT alongside spring security
## High Level Architecture
- Rest To Event Inititation
![Rest To Event Inititation](https://github.com/KazibweStephen/UgroMart-Platform/blob/main/High%20Level%20Rest-To-Event.jpeg?raw=true)

- Event Based Communication
![Event Based Communication](https://github.com/KazibweStephen/UgroMart-Platform/blob/main/UgroMartArchitecture.jpeg?raw=true)

### Documentation
The api layer(gateway to the platform) is documentated using swagger2 and can be accessed upon running this service on
[Swagger Documentation](http://localhost:8081/swagger-ui/)  , replace localhost with your app url, and 8080 with your app port

### Setup
- [Install Java 11 or above](https://java.com/en/download/help/download_options.html) 
- [Install Docker](https://docs.docker.com/docker-for-mac/install/)
- [Install Docker Compose, for non Mac Users](https://docs.docker.com/compose/install/)
- [Install Postman](https://www.postman.com/downloads/)

- [clone the repo(Switch to main branch, it is more stable)](https://github.com/KazibweStephen/UgroMart-Platform)
- Change your directory to the root directory of the platform code(Ugromart folder) with  your terminal or Command Prompt
- Run the following command in your terminal or console( You might need to run as root user or open command prompt as administrator)
    ```              sh ./build-apps.sh           ``` and give some time for all the services to start
- Check the status of the services as registered by Eureka herehttp://localhost:8761/
- Refer to the [Swagger Documentation](http://localhost:8081/swagger-ui/) http://localhost:8081/swagger-ui/  for how to use the rest api

### API usage
- Register your self as a User 
```json
url : http://localhost:8081/api/user/register
Method : POST
body: {
       "username":"kstephen6@gmail.com",
       "password":"12345"
      }
Headers:
Content-Type: application/json
```
- Login to get a toke to use for the rest of the requests. The response contains a token.
```json
url : http://localhost:8081/api/user/login
Method : POST
body: {
       "username":"kstephen6@gmail.com",
       "password":"12345"
      }
Headers:
  Content-Type: application/json
```
- Sample Products and Venders are created at app initialization. Do fetch these to enable contruction of order creation body.
```json
url : http://localhost:8081/api/products/
Method : GET

Headers:
  Content-Type: application/json
  Authorization: Bearer {token}

```

```json
url : http://localhost:8081/api/products/
Method : GET

Headers:
  Content-Type: application/json
  Authorization: Bearer {token}

```
- Create as order via
```json
url : http://localhost:8081/api/order/create
Method : POST
body: {
        "orderId": 0,
        "userId": 1,
        "orderDate": "2021-03-12",
        "totalOrder": {
          "amount": 12000.00
        },
        "status": "",
        "orderItems": [
            {
                "productId":3,
                "quantity": 3
            },
            {
                "productId":2,
                "quantity": 3
            }
         
        ]
      }
Headers:
  Content-Type: application/json
```
- Query for users orders for status
```json
url : http://localhost:8081/api/order/{userId}
Method : GET

Headers:
  Content-Type: application/json
  Authorization: Bearer {token}

```

### Clean Up
- Press ```control + C```  to stop the containers in the root directory
- Run ```docker-compose down``` to  remove containers and the network
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.0-M2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.0-M2/maven-plugin/reference/html/#build-image)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-security)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-amqp)

### Guides
The following guides illustrate how to use some features concretely:

* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)

