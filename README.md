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
1. [Install Java 11 or above](https://java.com/en/download/help/download_options.html) 
1. [Install Docker](https://docs.docker.com/docker-for-mac/install/)
1. [Install Docker Compose, for non Mac Users](https://docs.docker.com/compose/install/)
1. [Install Postman](https://www.postman.com/downloads/)
1. [Install Maven](https://maven.apache.org/install.html)

1. [clone the repo(Switch to main branch, it is more stable)](https://github.com/KazibweStephen/UgroMart-Platform)
   * Got Modules have been used to group the different microservices into one repo for manageability
   * Run the command below to pull the repo with its submodules
   ```markdown
       $ git clone --recurse-submodules https://github.com/KazibweStephen/UgroMart-Platform ```
1. Change your directory to the root directory of the platform code(Ugromart folder) with  your terminal or Command Prompt
1. Run the following command in your terminal or console( You might need to run as root user or open command prompt as administrator)
```markdown
sh ./build-apps.sh
```
  and give some time for all the services to start
1. Check the status of the services as registered by Eureka here (http://localhost:8761/)
![Registered Services Diagram](https://github.com/KazibweStephen/UgroMart-Platform/blob/main/Eureka%20Services%20registered.png?raw=true)

1. Refer to the [Swagger Documentation](http://localhost:8081/swagger-ui/) http://localhost:8081/swagger-ui/  for how to use the rest api

### Alternate Run 
 You can also change directory to each service's root directory with a separate Terminal
  1. Run the discovery microservice 
        ```markdown 
           cd discovery 
            ./mvnw clean package spring-boot:run -DskipTests=true ```
  1. Run the order microservice
        ```markdown 
           cd ordersms  ./mvnw clean package spring-boot:run -DskipTests=true ```
  1. Run the product microservice 
        ```markdown
         cd productsms  
         ./mvnw clean package spring-boot:run -DskipTests=true  ```
  1. Run the gateway microservice
        ```markdown 
            cd gateway  
            ./mvnw clean package spring-boot:run -DskipTests=true ```
  1. Run the payments microservice
        ```markdown 
           cd paymentsms  
           ./mvnw clean package spring-boot:run -DskipTests=true```
  
### API Usage
* We are using post man for which a collsction and environment has been created and shared
    * import [Ugromat Postman Request collection](https://www.getpostman.com/collections/1b162196438acbf176d0) 
    * Import and use the Enviroment shared here [Ugromart Postman environment](https://app.getpostman.com/join-team?invite_code=4968670f1e59b6a2e472964a68f8a1d0)
    * Run the requests as numbered in the collection(also shown belo)
    * Register Customer , 
    * Customer Login 
    * Fetch Products
    * Create Order
    * Inspect Customer Orders
    * Fetch Vendors
- An email will be sent out upon payment Success or Failure(test with number below)

- Not the for testing purposes each service uses and H2 database that looses data on restart of the service.
#### Order Payments Test Numbers
- An MTN momo pay sandbox has been used and for testing purposes, When creating customer, we cna use the following
 phone numbers to test out comes.
 
 
 |Number  |	Response |
 | :---:  | :---:
 | 46733123450 |	failed  |
 | 46733123451 |	rejected|
 | 46733123452 |	timeout |
 | 46733123453 |	ongoing(will answer pending first and if requested again after 30 seconds it will respond success)|
 | 46733123454 | pending |

### API USAGE 
 1 . Register your self as a User 
```markdown
url : http://localhost:8081/api/user/register
Method : POST
body: {
        "id": 0,
        "username": "...",[Required]
        "password": "12345",[Required]
        "passwordConfirm": "12345",[Required]
        "customerPhoneNumber": "...",[Required]
        "customerEmail": "...",[Required]
        "customerFullName": "...",[Required]
        "roles": []
      }
Headers:
Content-Type: application/json
```

 2 . Login to get a toke to use for the rest of the requests. The response contains a token.

```markdown
url : http://localhost:8081/api/user/login
Method : POST
body: {
      
          "password":"12345"[Required]
      }
Headers:
  Content-Type: application/json
```
 3 . Sample Products and Venders are created at app initialization. Do fetch these to enable contruction of order creation body.
```markdown
url : http://localhost:8081/api/products/
Method : GET

Headers:
  Content-Type: application/json
  Authorization: Bearer {token}

```

 4 . Create as order via
```markdown
url : http://localhost:8081/api/order/create
Method : POST
body: {
        "orderId": "",
        "userId": 2, [Required]
        "orderDate": "2021-03-20", [Required]
        "totalOrder": {
          "amount": 12000.00 [Required]
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
         
        ]   [Required]
      }
Headers:
  Content-Type: application/json
```
5 . Query for users orders for status
```markdown
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

