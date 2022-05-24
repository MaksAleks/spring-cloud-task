# This project aims to study spring-cloud-task

## What is spring-cloud-task needed for?
Spring Cloud Task makes it easy to create short-lived microservices. It provides capabilities that let short lived JVM processes be executed on demand in a production environment.

## Requirements

### Database
Spring Cloud Task uses a relational database to store the results of an executed task.
[Supported databases](https://docs.spring.io/spring-cloud-task/docs/current/reference/html/#:~:text=Spring%20Cloud%20Task%20currently%20supports%20the%20following%20databases%3A)

### Spring Runners

Spring includes many ways to bootstrap an application’s logic. Spring Boot provides a convenient method of doing so in an organized manner through its *Runner interfaces (CommandLineRunner or ApplicationRunner). A well behaved task can bootstrap any logic by using one of these two runners.

**( ! )** **IMPORTANT**:  
Any processing bootstrapped from mechanisms **other than a CommandLineRunner or ApplicationRunner is not recorded** by Spring Cloud Task.

