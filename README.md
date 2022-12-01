
# Nursery Plant Management
Nursery store REST API, where customer can shop for plants, add it into their cart and place orders and on the other hand nursery store owners can add plants into their store and check for customer orders.Backend of online Management where customer can buy plants, seeds.


![ERDiagram](https://user-images.githubusercontent.com/102217871/193451780-26ce45b3-a1bf-49d2-bb82-8424d6357f57.png)


. After running this program in spring boot we have to hit this url to see all the API's in swagger 
http://localhost:8880/swagger-ui/

ONLINE NURSERY PLANT and SEED MANAGMENT | Online plant and seed management where you can customer can buy and planter can sell thier plants and seeds. 
Service Layers, Data Access Layers to generate different end points. Features:

- **Services Offered**
1. User Login and Signup.
2. Addmin can add planter with plants and seeds.
3. Customer can buy plants and seeds.
4. Customer can see all orders history.

- **Backend**
1. Built authentication while login and logout using key.
2. Stored the data on MySQL and used it for Authentication.
3. Deployed the website on GitHub.
4. collaborative project built by a team of 4, delivered in 4 days.




* We have developed this REST API for an Nursery Plant Management. This API performs all the fundamental CRUD operations of any Online ordering plant with user validation at every step.
* This project is developed by team of 4 Back-end Developers during project week in Masai School. 

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL

## Modules

* Login, Logout Module
* Customer Module
* Admin Module
* Plant Module
* Seed Module
* Planter Module


## Features

* Customer and Admin authentication & validation with session uuid having.
* Admin Features:
    * Administrator Role of the entire application
    * Only registered admins with valid session token can add/update/delete Planter, plant and seed from main database
    * Admin can access the details of different customers, planter.
* Customer Features:
    * Registering themselves with application, and logging in to get the valid session token
    * Viewing list of available plant and seed.
    * Only logged in user can access his order, profile updation and other features.



## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](E-Commerce-Backend\src\main\resources\application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8880

    spring.datasource.url=jdbc:mysql://localhost:3306/sb201db;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root

```

## API Root Endpoint

`https://localhost:8880/`

`http://localhost:8880/swagger-ui.html`


## API Module Endpoints




### Admin Module

* `POST /admin/signup` : Register a new admin with proper data validation and admin session
* `POST /admin/login` : Admin can login with mobile number and password provided at the time of registation
* `POST /admin/logout` : Logging out admin based on session token
* `POST /admin/addplanter` :add planter with proper validation.
* `DELETE /admin/deleteplanter` : delete planter.
* `GET /admin/updateplanter` :Update Planter by id
* `GET /admin/viewAllPlanters` : Get list of all the planters
* `GET /admin/viewPlanterById` :Get Planter by Planter Id.
* `/viewplantersByMinMax/{mincost}/{maxcost}` :Get min cost and max cost.


### Customer Module

* `POST /user/signup` : Register a new user with proper data validation and user session
* `POST /user/login` : Admin can login with name and password provided at the time of registation
* `POST /user/logout` : Logging out user based on session token
* `POST /addOrder/{uuid}` : add a new order with proper data validation and customer session.
* `POST /driver/status/{?}` : Changes the status of the driver either Online or Offline
* `POST /user/update/` :Change the details of customer.
* `DELETE /user/delteOrder` :Delete the order from database.
* `GET /user/findorder` :Find order by id.
`etc..`

`POST   localhost:8880/login`

<table>
<tr>
<td>
<!-- We were a team of 4 from the Masai Web-18 batch. We worked on creating REST API and writing business logic for an E-commerce application. Our project performs fundamental operations of an e-commerce website, where our customer's data is validated, mapped, processed with business logic & persisted in the database. -->
  </td>
</tr>

</table>

## Technology Used

- **JAVA**
- **Spring**
- **Spring Boot**
- **Spring data JPA**
- **Hibernate**
- **MySQL**


## Contributors
👤 **Aftab Alam**

- GitHub: [@TheAftabAlam](https://github.com/Theaftabalam)

- LinkedIn: [@aftabalamsi12](https://www.linkedin.com/in/aftabalamsi12/)

👤 **Ashok Anumula**

- GitHub: [@Ashok Anumula](https://github.com/Anumulaashok)

- LinkedIn: [@Ashok Anumula](https://www.linkedin.com/in/ashoksmart143/)

👤 **Pavan Bajait**

- GitHub: [@Pavan Bajait](https://github.com/pavanbajait)

- LinkedIn: [@Pavan Bajait](https://www.linkedin.com/in/pavan-bajait/)


👤 **Pravin Nikam**

- GitHub: [@Pravin Nikam](https://github.com/pravindnikam07)

- LinkedIn: [@Pravin Nikam](https://www.linkedin.com/in/pravindnikam07/)



Footer
© 2022 GitHub, Inc.
Footer navigation
Terms
Privacy
Security
Status
Docs
Contact GitHub
API
Training
Blog
About
README.md at main.
