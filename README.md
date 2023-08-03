# 💻 CODE CHALLENGE

## 🚀 Run the application

### Requirements

* JDK 17
* Maven 3.9.2

### Commands

#### Compile sources

```script
mvn clean package
```

#### Execute the application

```script
mvn spring-boot:run -Dspring.profiles.active=dev
```

#### Execute without tests

```script
mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests
```

#### Run all the tests

```script
mvn clean test
```

or, in the case you are using IntelliJ IDEA, the run configurations should be loaded automatically:

![idea_run_configs.png](doc/idea_run_configs.png)

### Check results

You can access the swagger interface of the API in [this URL](http://localhost:8080/webjars/swagger-ui/index.html), so
you can check is the application is running and consume the implemented endpoint.

## 🔧 Other Tools

* Spring Boot 2.7.14
* OpenAPI 3
* OpenAPI maven code generator
* WebFlux (reactor)
* Cucumber 7
* JUnit5
* MapStruct
* Lombok

## 📋 Development notes

### Approach

For the design of the application, scalability has been taken into account, considering the possibility of high
concurrency situations. For this reason, certain decisions have been made in the architecture, such as the use of a
hexagonal architecture with reactive REST services in a potential microservices ecosystem. This approach allows us to
optimize the performance of our application under these circumstances.

Domain-Driven Design (DDD) has not been employed since the example's domain is not rich enough, making it pointless
in this context.

With this hexagonal architecture, combined with the use of the Command Query Responsibility Segregation (CQRS) pattern,
we achieve low coupling with our infrastructure and among the actions that should modify or not the persisted data in
our database. Besides, this architecture facilitates the adherence to SOLID principles and many of the best practices
guidelines.

The design of the API has been made following the API-first principles. This way, we could negotiate a contract with
the previously known consumers of the API, and every part can start coding with the knowledge of the endpoint paths and
the models each endpoint is going to receive and return.

### Possible future improvements

The application is not production-ready yet: it needs some security and improvements. Some of these could be:

* Use of Spring Security with OAuth2, as a way of authorization for clients to consume our endpoints. We could also
  extend OAuth with authentication using, for example, OpenID.
* We could use Actuator for monitoring the health and metrics of our service. We can also implement further tools to
  achieve this.
* We could consider the implementation of a Command & Query buses. This approach would decouple even more the underlying
  code of every layer.
* The data model purposed on the statement suggests that there are at least another two tables in our relational
  database: product and brand. I haven't implemented them to keep the challenge code easy, and also because I don't know
  how the microservices ecosystem would be built: maybe it wouldn't be necessary to persist those tables, or they might
  be persisted (in a more reduced version) and it might be necessary to populate them from other external services.
* The current implementation of CQRS pattern is basic: it just splits the read/write models and components. If read and
  write times were important enough, we could consider the possibility to upgrade it to a higher tier in which we would
  have one relational database (write) and one non-relational (read).
