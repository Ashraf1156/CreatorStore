# Developer Navigation Guide: Step-by-Step Backend Architecture

This guide outlines the exact chronological order and simple code-level steps followed to construct the CreatorStore backend application.

---

## Step 1: Project Setup and Security
* First, we create a fresh Spring Boot project using Spring Initializr.
* Second, we add the required starter dependencies for Web, JPA, PostgreSQL, Validation, and Lombok.
* Third, we create a `.env` file at the root folder to hide our private database password.
* Fourth, we add configuration lines inside our `DemoApplication.java` main method to load this `.env` file before the server boots up.

---

## Step 2: The Product Entity Layer
* First, we create a package named `entities`.
* Second, we create a Java class named `Product.java` inside this package.
* Third, we add class-level annotations like `@Entity` and `@Table(name="products")` to map this class directly to a database table.
* Fourth, we add Lombok annotations like `@Getter`, `@Setter`, and `@Builder` to generate boilerplate code automatically.
* Fifth, we add field-level validation restrictions like `@NotBlank` for the name and `@DecimalMin` for the price to prevent bad data.

---

## Step 3: The Product Repository and Service Layer
* First, we create a package named `repositories`.
* Second, we create an interface named `ProductRepository.java` that extends `JpaRepository`. This gives us built-in database methods like `.save()` and `.findAll()`.
* Third, we create a package named `service`.
* Fourth, we create a class named `ProductService.java` marked with the `@Service` annotation to hold our business logic.
* Fifth, we write five simple CRUD methods inside this service class to create, read, update, and delete products from the database.

---

## Step 4: The Product Controller Layer
* First, we create a package named `controllers`.
* Second, we create a class named `ProductController.java` marked with `@RestController` and `@RequestMapping("/api/products")`.
* Third, we inject our `ProductService` dependency into this class using Lombok's `@RequiredArgsConstructor`.
* Fourth, we write endpoints for POST, GET, PUT, and DELETE HTTP verbs.
* Fifth, we use the `@RequestBody` annotation to capture incoming raw JSON payloads and convert them into Java objects.
* Sixth, we add the `@Valid` annotation to enforce our field restrictions and reject bad client requests with a 400 error.

---

## Step 5: The Order DTO Security Layer
* First, we create a package named `dto` to separate our raw database entities from client request payloads.
* Second, we create a lightweight class named `OrderItemRequest.java` to accept only a `productId` and a `quantity` from the customer.
* Third, we create a main request class named `OrderRequest.java` to accept the `customerName`, `customerEmail`, and a nested `List<OrderItemRequest>`.
* Fourth, we add the `@Valid` annotation on the nested list so our validation checks cascade down into every single item row in the payload.

---

## Step 6: The Order Database Entities
* First, we go back to the `entities` package to build our relational database layout.
* Second, we create the parent class named `Order.java` to store global checkout attributes like `totalPrice`, `status`, and tracking timestamps.
* Third, we create the child junction class named `OrderItem.java` to store individual snapshot line items.
* Fourth, we map a `@ManyToOne` relationship from `OrderItem` back to both the parent `Order` entity and the target `Product` entity.

---

## Step 7: The Order Business Logic Layer
* First, we create an interface named `OrderRepository.java` inside our repository package.
* Second, we open our service package and create a brand-new class named `OrderService.java`.
* Third, we inject both `OrderRepository` and `ProductRepository` as `final` variables at the top of the class.
* Fourth, we create a checkout method named `createOrder` and mark it with the `@Transactional` annotation for safety.
* Fifth, we write a `for` loop inside this method to iterate through the client's requested item list.
* Sixth, inside the loop, we fetch the live product row, verify inventory stock levels, deduct stock quantities, and calculate dynamic prices.
* Seventh, we save the modified product stock changes and construct individual `OrderItem` records using the Builder design pattern.
* Eighth, we calculate the grand total price and save the complete order chain securely to our database.

---

## Step 8: The Order Controller Layer
* First, we open our controllers package and create a class named `OrderController.java`.
* Second, we map the top-level route path to `"/api/orders"` using the `@RequestMapping` annotation.
* Third, we map a `@PostMapping` route to hand over incoming `OrderRequest` payloads to our checkout service logic.
* Fourth, we map a generic `@GetMapping` route to run a SQL select query and fetch our complete global order history tracking list.
* Fifth, we map a specific `@GetMapping("/{id}")` route to search for a single unique order row using an explicit path variable lookup binding.
