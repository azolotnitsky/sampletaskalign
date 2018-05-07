### Usage

- Run the application and go on http://localhost:8080/
- Use the following urls to invoke controllers methods 
    * `GET /api/product/{name}/{brand}`: to retrieve single product by product name and brand
    * `POST /api/product`: to create new product
    * `PUT /api/product/{name}/{brand}` to update a product
    * `DELETE /product/{name}/{brand}` to delete product
    * `GET /product/leftovers` to get all leftovers

### Build and run

#### Prerequisites

- Java 7
- Maven 3

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

or

   $java -jar SpringBootSampleTask-1.0.0.jar

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.