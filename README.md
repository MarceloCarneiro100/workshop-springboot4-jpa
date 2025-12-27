# 📚 Projeto de estudo Course API

🌐 Idiomas disponíveis: [Português](README.md) | [English](README.en.md)

Aplicação Spring Boot para gerenciar usuários, pedidos, produtos, categorias e pagamentos. Estrutura em camadas (entities, repositories, services, resources) com JPA/Hibernate e banco H2 em memória para o perfil de teste.

---

## 🚀 Tecnologias
- Java 25
- Spring Boot
- Spring Data JPA / Hibernate
- H2 Database (test)
- RESTful API
- Jackson (JSON)
- Maven (Maven Wrapper: mvnw / mvnw.cmd)

---

## 🏗️ Arquitetura e componentes
- Entities: User, Order, Product, Category, OrderItem (chave composta via `@EmbeddedId`), Payment (OneToOne com Order usando `@MapsId`).
- Repositories: Interfaces estendem `JpaRepository` com CRUD automático.
- Services: Lógica de negócio e exceções customizadas:
  - `ResourceNotFoundException` — recurso não encontrado (404).
  - `DatabaseException` — violação de integridade.
- Resources (Controllers): Endpoints REST para usuários, pedidos, produtos e categorias.
- Erros padronizados: `StandardError` (timestamp, status, error, message, path) via `ResourceExceptionHandler`.

---

## 📊 Modelo de Domínio (UML)

Aqui é apresentado o **diagrama UML do modelo de domínio**, mostrando as entidades e seus relacionamentos principais.

![Image](https://github.com/user-attachments/assets/89d6c4e4-1bff-4a51-85fb-f4457e3bc043)


## 📦 Modelo de Instância (Objetos UML)

Esse diagrama mostra exemplos concretos de objetos criados a partir das entidades, já com valores atribuídos.

![Image](https://github.com/user-attachments/assets/21e28530-296f-43c6-8405-550462ee32eb)


## ⚙️ Configurações

### application.properties
spring.application.name=course  
spring.profiles.active=test  
spring.jpa.open-in-view=true  

### application-test.properties
#### DATASOURCE  
spring.datasource.driverClassName=org.h2.Driver  
spring.datasource.url=jdbc:h2:mem:testdb  
spring.datasource.username=sa  
spring.datasource.password=  

#### H2 CLIENT  
spring.h2.console.enabled=true  
spring.h2.console.path=/h2-console  

#### JPA, SQL  
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect  
spring.jpa.defer-datasource-initialization=true  
spring.jpa.show-sql=true  
spring.jpa.properties.hibernate.format_sql=true  

---

## 🔗 Endpoints principais
- Usuários:
  - GET `/users`
  - GET `/users/{id}`
  - POST `/users`
  - PUT `/users/{id}`
  - DELETE `/users/{id}`
- Pedidos:
  - GET `/orders`
  - GET `/orders/{id}`
- Produtos:
  - GET `/products`
  - GET `/products/{id}`
- Categorias:
  - GET `/categories`
  - GET `/categories/{id}`

---

## 🛠️ Execução via terminal

📌 Onde devo estar?  
Sempre na **pasta raiz do projeto** chamada workshop-springboot4-jpa, onde estão o `pom.xml`, `mvnw` e `mvnw.cmd`.

### ▶️ Linux / macOS
cd /caminho/para/workshop-springboot4-jpa
- ./mvnw spring-boot:run  

### ▶️ Windows (Prompt de Comando ou PowerShell)
cd C:\caminho\para\workshop-springboot4-jpa
-   mvnw spring-boot:run

### 🔄 Alternativa (sem Maven Wrapper, requer Maven instalado no PATH)
- Linux / macOS:  
mvn spring-boot:run  
- Windows:  
mvn spring-boot:run  

🌐 Acesso:  
- API: http://localhost:8080  
- H2 Console: http://localhost:8080/h2-console  
  - JDBC URL: `jdbc:h2:mem:testdb`  
  - User: `sa`  
  - Password: (vazio)  

---

## 🛠️ Executando no IntelliJ IDEA (passo a passo)

1. **Abrir o projeto:**
   - No IntelliJ, vá em *File → Open* e selecione a **pasta raiz do projeto** que você clonou, chamada `workshop-springboot4-jpa`.
   - O IntelliJ reconhecerá automaticamente que se trata de um projeto **Maven** e fará a importação das dependências.

2. **Configurar JDK:**
   - Acesse *File → Project Structure → Project*.
   - Em *Project SDK*, selecione **Java 25**.
   - Em *Modules*, aplique o mesmo SDK para garantir consistência.

3. **Sincronizar dependências:**
   - Aguarde a importação do Maven.
   - Se necessário, abra o painel *Maven* e clique em **Reload All Maven Projects** para atualizar as dependências.

4. **Perfil de execução:**
   - O projeto já define `spring.profiles.active=test` em `application.properties`.
   - Não é necessário configurar opções adicionais de VM.

5. **Executar a aplicação:**
   - Localize a classe principal (ex.: `CourseApplication`, anotada com `@SpringBootApplication`).
   - Clique no ícone de **Run** ▶️ ao lado do método `main` ou use *Run → Run 'CourseApplication'*.

6. **Acessar a API:**
   - Base URL: `http://localhost:8080`
   - Teste os endpoints via Postman, Insomnia ou navegador (para requisições GET).

7. **Console H2:**
   - Acesse `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `sa`
   - Password: (vazio)
   - Clique em **Connect** para visualizar tabelas e dados.

![Image](https://github.com/user-attachments/assets/4f43abb8-ad51-400d-b760-3aabe719953d)

---


## 📌 Destaques técnicos
- **OrderItem:** usa `@EmbeddedId` para chave composta (`OrderItemPK`) relacionando Order e Product; método `getSubTotal()` calcula subtotal.
- **Payment:** mapeado com `@OneToOne` e `@MapsId` para compartilhar a chave do `Order`.
- **Serialização JSON:** `@JsonIgnore` aplicado onde necessário para evitar ciclos (ex.: `User.orders`, `OrderItem.getOrder()`, `Payment.order`).
- **Erros padronizados:** `ResourceExceptionHandler` retorna `404` para não encontrado e `409` para conflitos de banco, ambos com payload `StandardError`.

---

## ✨ Conclusão
API REST compacta, organizada e pronta para evoluir (validações, paginação, segurança). Base sólida para e-commerce/gestão de pedidos, com execução simples em Windows e Linux/macOS e ambiente de testes via H2.
