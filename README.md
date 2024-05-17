# Microservices 101

## Overview
This project is a microservices-based mainly backend software that provides services for accounts, loans, and cards. It aims to demonstrate the capabilities of a distributed architecture.

### Services
- **Account Service**: Manages user accounts, including account creation, updates, and balance inquiries.
- **Loan Service**: Handles loan applications.
- **Card Service**: Manages credit and debit card issuance, transactions.

## Technology Stack
- **Spring Boot**: Simplifies the bootstrapping and development of new Spring applications.
- **Docker**: Used for containerizing the microservices and ensuring consistency across various development and production environments.
- **Kubernetes**: Orchestrates container deployment, scaling, and management.
- **MySQL/PostgreSQL**: Acts as the primary database for storing persistent data.
- **RabbitMQ/Kafka**: Messaging systems for asynchronous communication between microservices.
- **Eureka**: Service discovery mechanisms to manage microservices.
- **Spring Cloud Gateway**: Provides dynamic routing, monitoring, resiliency, security, and more.

### Prerequisites
- JDK 17
- Docker
- Kubernetes
- Maven

### Installation
1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/microservices.git
   cd microservices
