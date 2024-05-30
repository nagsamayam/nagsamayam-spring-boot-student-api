# Spring Boot, MySQL, JPA, Hibernate Rest API

Build Restful CRUD API for a simple Note-Taking application using Spring Boot, Mysql, JPA and Hibernate.

## Requirements

1. Java - 21.x

2. Gradle - 8.x.x

3. Mysql - 8.0.28

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/nagsamayam/spring-boot-student-api.git
```

**2. Create Mysql database**
```bash
create database mypass_student
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.yml`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using gradle**

```bash
export MYSQL_PASSWORD=<YOUR_MYSQL_PASSWORD>
./gradlew build
java -jar ./build/libs/student-0.0.1-SNAPSHOT.jar
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    POST /api/v1/auth/register

    POST /api/v1/auth/authenticate

    GET /api/v1/students
    
    POST /api/v1/students

    GET api/v1/students/api/v1/students/all-with-pagination?page=1&perPage=10

    GET api/v1/students/all-with-pagination-and-sort?sortBy=id&sortDir=asc
    
    GET /api/v1/students/{studentUuid}
    
    PUT /api/v1/students/{studentUuid}
    
    DELETE /api/v1/students/{studentUuid}

    GET /api/v1/schools

    POST /api/v1/schools

You can test them using postman or any other rest client.
