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

## 🔧 Other Tools

* Spring Boot 2.7.14
* OpenAPI 3
* OpenAPI maven code generator
* WebFlux
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

Domain-Driven Design (DDD) has not been employed since the example's domain is not rich enough, making it unnecessary
in this context.

With this hexagonal architecture, combined with the use of the Command Query Responsibility Segregation (CQRS) pattern,
we achieve low coupling with our infrastructure and among the actions that should modify or not the persisted data in
our database.

* API First

### Appreciations

### Assumptions

### Possible improvements
