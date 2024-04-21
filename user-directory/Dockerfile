FROM openjdk:17-oracle
EXPOSE 8080
ADD target/user-directory.jar user-directory.jar
ENTRYPOINT ["java", "-jar", "user-directory.jar"]
LABEL version="1.0"
LABEL description="User Directory Application"