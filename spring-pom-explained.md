## POM FOR SPRING

##### Start of pom and XML declaration with xml namespace to tell where to get definations
```XML
<?xml version="1.0" encoding="UTF-8" ?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
```
##### MANDATORY INFO RELATED TO PROJECT
###### Model version tells pom version
```XML    
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.amathur</groupId>
    <artifactId>address-book</artifactId>
    <version>1.0.0</version>
    <description>Spring Boot Application to handle addresses</description>
    <name>Address Book</name>
```
##### Properties, if any
```XML    
    <properties>
        <java.version>17</java.version>
    </properties>
```
### DEPENDENCIES
```XML
    <dependencies>
```
##### For basic Spring configurations like @Repository, @Controller, etc
##### Contains embedded servlet containers
```XML
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
```
##### For basic Spring simplifications and configuring DispatcherServlet, error handling, and other web-related component
```XML
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
```
##### For @Data and other code completions
```XML
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
```
#####  For @Entity, @Id, @GeneratedValue, @Table etc
```XML
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
        </dependency>
```
##### For data validations in DTO like @NotNull @Size etc
```XML
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
        </dependency>
```
##### Validation started working with this. With only jakarta.validation-api it doesnt work
```XML
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
```
##### For JpaRepository extended in any repository interface
```XML
        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-jpa</artifactId>
        </dependency>
```
##### Required even after spring jpa. Spring JPA provides base infra and boot starter jpa provides configurations for it
##### Includes Drivers and auto-configuration for key components such as DataSource, EntityManagerFactory, TransactionManager
```XML
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
```
##### H2, in-memory Database
```XML
<!--        Database-->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <artifactId>h2</artifactId>
        </dependency>
    </dependencies>
```
##### Build related info and FINAL NAME OF THE JAR
```XML
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
        <finalName>address-book</finalName>
    </build>
</project>
```

## Copyright - Aditya Mathur