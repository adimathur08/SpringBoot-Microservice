FROM openjdk:17-oracle
EXPOSE 8081
ADD target/address-book.jar address-book.jar
ENTRYPOINT ["java", "-jar", "address-book.jar"]
LABEL version="1.0"
LABEL description="Address Book Application"