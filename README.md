## BASIC MICROSERVICE ARCH

### Tech Stack

- Spring Boot
- Docker
- Java [17]
- Database : `H2`

### Working

- There are 2 microservices
> - User Directory
> - Address Book

- `User Directory` is called by the client directly to perform CRUD operations
- `User Directory` internally calls `Address Book` to handle the `Address` for any `User`.


### No Service registry is used. Instead create a 


#### Points to note:

- All CRUD operations are atomic, and are validated before saving. [Done Manually using Validation]
- Proper JSON format is used for all APIs response.
- `@ExceptionHandling` is used as direct handling of Exceptions (like `FeignException`) and custom Wrapped Exceptions (like `UserNotFoundException`)
- Logging using `sout` is done at all places with `[tags]` to mimic classes and modules like any Logger .

## HOW TO RUN?

- Build both projects using `mvn clean install` or `mvn clean package`
- Use the commands in both the project folders :
  >
  > `docker network create <network-name>`

  > `cd /user-directory/`
  > 
  > `docker build -t user-directory:1.0 .`
  > 
  > `docker run --network <network-name> -d -p 8080:8080 --name user-directory-service user-directory:1.0`
  
  > `cd /address-book/`
  >
  > `docker build -t address-book:1.0 .`
  >
  > `docker run --network <network-name> -d -p 8081:8081 --name address-book-service address-book:1.0`

**User [build-project1.ps1](build-project1.ps1) file to automate these Docker steps or have a more clear understanding.**


## APIs
[ NO UPDATE APIs ARE IMPLEMENTED ]

### User Directory
> Please Note that the User Directory hits the Address Book using container name (present in same docker network) by the name of **`address-book-service`**

>Port : 8080
- Get a User : `http://<localhost/ip>:8080/user/find/{id}`
- Get all Users : `http://<localhost/ip>:8080/user/find/all`
- Remove Users : `http://<localhost/ip>:8080/user/remove/{id}`
- Save Users : `http://<localhost/ip>:8080/user/save/`
  
  >JSON format required
   ```JSON 
  {
      "name" : "Aditya",
      "address":
      {
        "city":"Delhi",
        "country":"India",
        "pincode":123456
      },
      "email":"aditya@gmail.com",
      "age":20
   }
   ```

### Address Book
> Address Book APIs are public and not hidden for this implementation

>Port : 8081
- Get an Address : `http://<localhost/ip>:8081/address/find/{id}`
- Get all Address : `http://<localhost/ip>:8081/address/find/all`
- Remove Address : `http://<localhost/ip>:8081/address/remove/{id}`
- Save Address : `http://<localhost/ip>:8081/address/save/`

  >JSON format required
   ```JSON 
  {
    "city":"Delhi",
    "country":"India",
    "pincode":123456
   }
   ```

## **Copyright - [Aditya Mathur](https://github.com/adimathur08) :)**

