# E-commerce Microservices Project

This repository contains a comprehensive e-commerce microservices architecture, designed to handle the full range of operations required in an e-commerce system. The project is developed using various microservices for customers, products, orders, notifications, and payments.

Each service is independent and communicates via asynchronous messaging through Kafka, allowing the system to be scalable, resilient, and loosely coupled. The system includes service discovery, centralized configuration, and monitoring tools to ensure smooth operation in a cloud-native environment.

## Microservices Overview

### 1. Customer Service
 - Manages customer-related operations such as registration, authentication, and profile management. 
 - Integrated with **Keycloak** for authentication and authorization.

### 2. Product Service
 - Manages product-related operations such as product creation, retrieval, and updating.

### 3. Order Service
 - Processes customer orders and manages the lifecycle of an order.
 - Stores order information in PostgreSQL for relational data consistency.
 - Communicates with the Payment Service to handle payment status.

### 4. Payment Service
 - Manages payment processing for orders, including payment status and transaction records.
 - Communicates with the Order Service to update order status based on payment status.

### 5. Notification Service
 - Sends notifications to customers about order status changes. 
 - Uses MailDev for email notifications.

### 6. Kafka Service
 - Provides a Kafka broker for asynchronous communication between services.
 - Uses Kafka topics for message passing between services.

### 7. Config Service
 - Centralized configuration management for all services.
 - Uses Spring Cloud Config Server to manage configurations.

### 8. Discovery Service
 - Service discovery and registration for all services.
 - Uses Eureka Server for service discovery.

### 9. Gateway Service
 - API Gateway for routing requests to the appropriate services.
 - Uses Spring Cloud Gateway for routing and load balancing.

### 10. Monitoring Service
 - Uses Zipkin and Zookeeper for distributed tracing and monitoring.
 - Provides insights into service communication and performance.

## Architecture Overview

This project follows a microservices architecture where each service is independently deployed and maintained. The services communicate using both synchronous (HTTP REST) and asynchronous (Kafka) messaging patterns. 

The architecture includes the following technologies:

 - **Spring Boot** - for building each microservice.
 - **Spring Cloud Netflix Eureka** - for service discovery.
 - **Spring Cloud Config** - for centralized configuration management.
 - **Apache Kafka** - for messaging between services.
 - **Zipkin** - for distributed tracing.
 - **Keycloak** - for user authentication and authorization.
 - **MongoDB** - for non-relational database management.
 - **PostgreSQL** - for relational database management.
 - **Docker** and **Docker Compose** - for containerization.
 - **MailDev** - for email testing.

The architecture is designed to be scalable, resilient, and loosely coupled, allowing each service to be developed, deployed, and maintained independently.

![Architecture Overview](/resources/architecture-diagram.png)

## Getting Started

To run the project, you need to have Docker and Docker Compose installed on your machine.

1. Clone the repository:

```bash
git clone https://github.com/PoojithaIrosha/ecommerce-microservices.git
cd ecommerce-microservices
```

2. Start the services using Docker Compose:

```bash
docker-compose up
```
This command will start all the services, including the Customer Service, Product Service, Order Service, Payment Service, Notification Service, Kafka Service, Config Service, Discovery Service, Gateway Service, and Monitoring Service.


3. Start Services Individually:


## Conclusion

This project provides a comprehensive e-commerce microservices architecture that demonstrates the power of microservices in building scalable, resilient, and loosely coupled systems. The project includes various microservices for customers, products, orders, notifications, and payments, along with service discovery, centralized configuration, and monitoring tools.

The project is designed to be cloud-native, allowing it to be deployed on any cloud platform or on-premises infrastructure. The use of Docker and Docker Compose makes it easy to run the project locally for testing and development purposes.

Overall, this project serves as a great example of how microservices can be used to build complex systems that are flexible, scalable, and maintainable.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


