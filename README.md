# Java Practical Test – Spring Boot Application

This project is a Spring Boot application built as part of a Java practical test.

---

## Prerequisites

Before running the application, ensure the following are installed on your machine:

### 1. Java 17
Download and install **Java 17 (JDK)** from the official Oracle archive:

https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

Verify installation:
```
java -version
```

---

### 2. Maven 3.8.6
Download and install **Apache Maven 3.8.6**:

https://maven.apache.org/docs/3.8.6/release-notes.html

Verify installation:
```
mvn -version
```

---

## Clone the Repository

```
git clone https://github.com/Joesta/java-practical-test.git
cd java-practical-test
```

---

## Build the Application

```
mvn clean install
```

This command downloads all required dependencies and builds the project.

---

## Run the Application

```
mvn spring-boot:run
```

The application will start on:

http://localhost:8080

---

## API Usage

Example endpoint (if exposed):

```
GET http://localhost:8080/users
```

---

## Troubleshooting

- Ensure JAVA_HOME points to Java 17
- Ensure Maven is added to PATH
- Ensure port 8080 is free
- Re-run `mvn clean install` if dependency issues occur

---

## Technologies Used

- Java 17
- Spring Boot
- Maven
- MapStruct
- Spring Data JPA

---

## Author

Joesta Sebolela  
https://github.com/Joesta
