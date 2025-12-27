# 📚 Study Project Course API

🌐 Available languages: [Portuguese](README.md) | [English](README.en.md)

Spring Boot application to manage users, orders, products, categories, and payments. Layered structure (entities, repositories, services, resources) with JPA/Hibernate and in-memory H2 database for the test profile.

---

## 🚀 Technologies
- Java 25
- Spring Boot
- Spring Data JPA / Hibernate
- H2 Database (test)
- RESTful API
- Jackson (JSON)
- Maven (Maven Wrapper: mvnw / mvnw.cmd)

---

## 🏗️ Architecture and Components
- Entities: User, Order, Product, Category, OrderItem (composite key via `@EmbeddedId`), Payment (OneToOne with Order using `@MapsId`).
- Repositories: Interfaces extend `JpaRepository` with automatic CRUD.
- Services: Business logic and custom exceptions:
  - `ResourceNotFoundException` — resource not found (404).
  - `DatabaseException` — integrity violation.
- Resources (Controllers): REST endpoints for users, orders, products, and categories.
- Standardized errors: `StandardError` (timestamp, status, error, message, path) via `ResourceExceptionHandler`.

---

## 📊 Domain Model (UML)

This section presents the **UML diagram of the domain model**, showing the entities and their main relationships.

![Image](https://github.com/user-attachments/assets/89d6c4e4-1bff-4a51-85fb-f4457e3bc043)

---

## 📦 Instance Model (UML Objects)

This diagram shows concrete examples of objects created from the entities, already with assigned values.

![Image](https://github.com/user-attachments/assets/21e28530-296f-43c6-8405-550462ee32eb)

---

## ⚙️ Configuration

### application.properties
spring.application.name=course  
spring.profiles.active=test  
spring.jpa.open-in-view=true  

### application-test.properties
#### Datasource  
spring.datasource.driverClassName=org.h2.Driver  
spring.datasource.url=jdbc:h2:mem:testdb  
spring.datasource.username=sa  
spring.datasource.password=  

#### H2 Console  
spring.h2.console.enabled=true  
spring.h2.console.path=/h2-console  

#### JPA, SQL  
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect  
spring.jpa.defer-datasource-initialization=true  
spring.jpa.show-sql=true  
spring.jpa.properties.hibernate.format_sql=true  

---

## 🔗 Main Endpoints
- Users:
  - GET `/users`
  - GET `/users/{id}`
  - POST `/users`
  - PUT `/users/{id}`
  - DELETE `/users/{id}`
- Orders:
  - GET `/orders`
  - GET `/orders/{id}`
- Products:
  - GET `/products`
  - GET `/products/{id}`
- Categories:
  - GET `/categories`
  - GET `/categories/{id}`

---

## 🛠️ Running via Terminal

📌 Where to be:  
Always in the **project root folder** called workshop-springboot4-jpa, where `pom.xml`, `mvnw`, and `mvnw.cmd` are located.

### ▶️ Linux / macOS
cd /path/to/workshop-springboot4-jpa  
./mvnw spring-boot:run  

### ▶️ Windows (Command Prompt or PowerShell)
cd C:\path\to\workshop-springboot4-jpa  
mvnw spring-boot:run  

### 🔄 Alternative (without Maven Wrapper, requires Maven installed in PATH)
- Linux / macOS:  
mvn spring-boot:run  
- Windows:  
mvn spring-boot:run  

🌐 Access:  
- API: http://localhost:8080  
- H2 Console: http://localhost:8080/h2-console  
  - JDBC URL: `jdbc:h2:mem:testdb`  
  - User: `sa`  
  - Password: (empty)  

---

## 🛠️ Running in IntelliJ IDEA (step by step)

1. **Open the project:**
   - In IntelliJ, go to *File → Open* and select the **project root folder** you cloned, named `workshop-springboot4-jpa`.
   - IntelliJ will automatically detect it as a **Maven** project and import dependencies.

2. **Configure JDK:**
   - Go to *File → Project Structure → Project*.
   - In *Project SDK*, select **Java 25**.
   - In *Modules*, apply the same SDK for consistency.

3. **Synchronize dependencies:**
   - Wait for Maven import.
   - If needed, open the *Maven* panel and click **Reload All Maven Projects** to update dependencies.

4. **Execution profile:**
   - The project already defines `spring.profiles.active=test` in `application.properties`.
   - No additional VM options are required.

5. **Run the application:**
   - Locate the main class (e.g., `CourseApplication`, annotated with `@SpringBootApplication`).
   - Click the **Run** ▶️ icon next to the `main` method or use *Run → Run 'CourseApplication'*.

6. **Access the API:**
   - Base URL: `http://localhost:8080`
   - Test endpoints via Postman, Insomnia, or browser (for GET requests).

7. **H2 Console:**
   - Access `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `sa`
   - Password: (empty)
   - Click **Connect** to view tables and data.

![Image](https://github.com/user-attachments/assets/4f43abb8-ad51-400d-b760-3aabe719953d)

---

## 📌 Technical Highlights
- **OrderItem:** uses `@EmbeddedId` for composite key (`OrderItemPK`) linking Order and Product; method `getSubTotal()` calculates subtotal.
- **Payment:** mapped with `@OneToOne` and `@MapsId` to share the key with `Order`.
- **JSON Serialization:** `@JsonIgnore` applied where necessary to avoid cycles (e.g., `User.orders`, `OrderItem.getOrder()`, `Payment.order`).
- **Standardized errors:** `ResourceExceptionHandler` returns `404` for not found and `409` for database conflicts, both with `StandardError` payload.

---

## ✨ Conclusion
Compact, well-organized REST API ready to evolve (validations, pagination, security). Solid foundation for e-commerce/order management, with simple execution on Windows and Linux/macOS and test environment via H2.
