# 🚨 ADE CODING CHALLENGE 🚓

**ADE Coding Challenge** A API Rest has been requested to manage stolen bikes.

### 🚀 Deployed on Heroku
[Heroku Endpoint](https://ade-coding-challenge.herokuapp.com/ "Web")

## 💻 Features

- ✅ Bike owners can report a stolen bike.
- ✅ Bike ENTITY have diferents characteristics: Licence Number, Color, Type, Full Name of the Owner, Date, Description
  of the theft, Address where the bike was stolen and Status of the case.
- ✅Police have multiple departaments
- ✅Relationship 1 - X ( Departments can have some amount of police officers)
- ✅Search endpoint to filter with Color, Type and Status at the same time
- ✅If one bike is added and also one police officer is available the system assign one police to this stolen bike
- ✅One police officer can only manage one bike at the same time
- ✅When one police find one bike he change the status of the bike and his status and can start find another
- ✅System assign automatically stolen bikes to police officers
- ✅**Extra** The mail library has been integrated and through Gmail we make the notification that the bike has been
  found.
- ✅**Extra** Implemented OpenAPI.

## 🛠️Build

* [Java](https://docs.oracle.com/en/java/javase/11/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [MySQL - JPA - Hibernate]()

### Installation

1. As a first step you will have to clone this repository
   ```sh

   git clone https://github.com/AlbertoLinde/ADECodingChallenge.git

   ```

2. You will need to modify the application.properties, you will need to add the URL of your database, the password and
   to be able to send eMails through Google you will need to create an application password.

3. Once you have modified these data. You can proceed to start the application through the console or your IDE.

4. If you have Maven installed correctly you will simply need to run

```sh
     mvn spring-boot:run
```

5. If you have Maven installed correctly you will simply need to run

```sh
		mvn package
		java -jar .\target\CodingChallenge-0.0.1-SNAPSHOT.jar
   ```

5. If you have Maven installed correctly you will simply need to run

**REQUIREMENTS FOR LOCAL DEVELOPMENT: MySQL and JAVA**

## ⚠️Pending Features

* Use GeoCode library to find the coordinates
* Implement Role Based Control
* Improve BD Modeling: Add Case table

## ⬇️ EndPoints

Request URL: /api/v1

#### Bike Controller

- [POST] Crete new Bike: /addBike
- [GET] Find Bike by ID: /bike/{id}
- [GET] Find Bike by Licence Number: /bike/licence-number/{licenceNumber}
- [GET] Find Bike by Color: /bike/color/{color}
- [GET] Find Bike by Type: /bike/type/{type}
- [GET] Find bike by Stolen Status: /bikes/stolen-status/{stolen-status}
- [GET] Find Bike by Color or Type or Status: /bikes/findByColorOrTypeOrStatus (Request Params. Ex:
  url?color=blue&type=mountain&status=true)
- [PUT] Update stolen status to false by ID: /bike/{id}
- [PUT] Assign all free Police Officers to Stolen Bikes: /bikes/assign-polices
- [PUT] Set bike has been found by officer: /bike/found-bike/{id}
- [DELETE] /bike/{id}
- [DELETE] /delete-bikes

#### Police Controller

- [POST] Create Police Officer: /add-police
- [GET] Find Police Officer by ID: /police/{id}
- [GET] Find All Police Officers: /police-officers
- [GET] Find All Police Officers currently investigating: /police/investigating
- [GET] Find All Police Officers currently not investigating: /police/not-investigating
- [PUT] Assign one Police Officer to specific deparment /police/{policeId}/department/{departmentId}


#### Police Deparment

- [POST] Create Police Department: /add-department
- [GET] Find Police Department by ID: /department/{id}
- [GET] Find All Police Departments: /departments

## Contacto 📩

[LinkedIn](www.linkedin.com/in/albertolinde "LinkedIn") - [Twitter](https://twitter.com/AlberALinde "Twitter")

- [Web](https://www.albertolinde.com/ "Web") - abreulindealberto@gmail.com