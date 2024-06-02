# Sales Application

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-4B7BE5?style=for-the-badge&logo=java&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)

## Description

This project is a sales application developed during a bootcamp. It uses Java, Spring, JPA, and Hibernate to create a robust and scalable application for managing products, orders, and customers.

## Features

- **Product Management:** Add, edit, and remove products from the catalog.
- **Customer Management:** Register new customers and update existing information.
- **Order Management:** Create, update, and view purchase orders.
- **Authentication and Authorization:** Use Spring Security to ensure application security.

## Entities

- **Category:** Product categories.
- **Order:** Customer orders.
- **OrderItem:** Individual items within an order.
- **Payment:** Payment information for orders.
- **Product:** Products available for sale.
- **User:** System users.

## Project Structure

The project is divided into the following modules:

- **entities:** Contains the entity classes that represent the application's data.
- **repositories:** Contains the repository interfaces for data access.
- **services:** Contains the service classes that implement business logic.
- **resources:** Contains the REST controllers that expose the API.
- **config:** Contains the Spring configuration classes.

## Technologies Used

- **Java:** Main programming language of the project.
- **Spring Framework:** Used to simplify and enhance the development of Java applications.
- **Spring Boot:** Eases project setup and configuration.
- **JPA (Java Persistence API):** Provides a standard way to map Java objects to relational databases.
- **Hibernate:** JPA implementation for data management.
- **Spring Security:** Framework for implementing authentication and authorization.

## Prerequisites

To run this project, you will need to have installed:

- [Java JDK 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/) (or another database of your choice)

## How to Run

1. Clone this repository:
    ```bash
    git clone https://github.com/your-username/your-repository.git
    cd your-repository
    ```

2. Configure the database in `application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. Compile and run the project with Maven:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. Access the application:
    ```
    http://localhost:8080
    ```

## Contributions

Contributions are welcome! If you wish to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a branch for your feature: `git checkout -b my-feature`
3. Commit your changes: `git commit -m 'Add my feature'`
4. Push to the branch: `git push origin my-feature`
5. Open a Pull Request.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

Jôsevan Tárcio Silva de Oliveira - [LinkedIn](https://www.linkedin.com/in/your-linkedin-profile/)
