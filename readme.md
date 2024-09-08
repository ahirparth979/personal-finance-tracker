---

# Personal Finance Tracker

The **Personal Finance Tracker** is a web application designed to help users manage and track their finances. It offers features like login, registration, and personal finance data visualization, and is built with a Spring Boot backend and a ReactJS frontend.

## Project Overview

### Backend
The backend is developed using **Spring Boot** and exposes a set of RESTful APIs to handle user authentication and financial data management. The backend uses JWT for secure authentication.

### Frontend
The frontend is developed using **ReactJS** and provides a user-friendly interface to interact with the finance tracker. The app includes login, registration, and a dashboard to display user-specific data.

---

## Table of Contents

1. [Project Features](#project-features)
2. [Technologies Used](#technologies-used)
3. [Getting Started](#getting-started)
    - [Backend Setup](#backend-setup)
    - [Frontend Setup](#frontend-setup)
4. [Project Structure](#project-structure)
5. [API Endpoints](#api-endpoints)
6. [Contributing](#contributing)
7. [License](#license)

---

## Project Features

- **User Authentication**: Login, registration, and logout functionalities secured with JWT.
- **Dashboard**: Displays personal finance data in an intuitive format.
- **Responsive Design**: Frontend UI adapts across devices.
- **Spring Boot Backend**: Handles authentication, session management, and APIs.
- **ReactJS Frontend**: Offers a clean and easy-to-use interface.
  
---

## Technologies Used

### Backend
- **Spring Boot** (Java)
- **Spring Security** with JWT
- **Hibernate** (JPA)
- **PostgreSQL** (or any RDBMS)

### Frontend
- **ReactJS**
- **HTML5 / CSS3**
- **JavaScript (ES6)**
- **Axios** for API requests

---

## Getting Started

### Prerequisites
Ensure you have the following installed:
- **Node.js** (v18+)
- **Java** (JDK 17+)
- **Maven** (for backend)

### Backend Setup

1. **Clone the repository** and navigate to the backend folder:
    ```bash
    git clone https://github.com/ahirparth979/personal-finance-tracker.git
    cd personal-finance-tracker/backend
    ```

2. **Configure the Database**:
    - Update the `application.properties` with your PostgreSQL credentials:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
      spring.datasource.username=root
      spring.datasource.password=yourpassword
      ```

3. **Build and Run the Backend**:
    ```bash
    mvn clean install
    java -jar target/personal-finance-tracker-backend.jar
    ```

4. **API Documentation**: The backend exposes the following APIs (for user management):
    - `POST /api/users/login`: Login API
    - `POST /api/users/register`: Register new users
    - `GET /api/users/me`: Get current user details

### Frontend Setup

1. Navigate to the frontend folder:
    ```bash
    cd ../frontend
    ```

2. Install dependencies:
    ```bash
    npm install
    ```

3. Start the React development server:
    ```bash
    npm start
    ```

4. The app will be accessible at `http://localhost:3000`.

---

## Project Structure

```bash
personal-finance-tracker/
│
├── backend/
│   ├── src/               # Java code for the backend
│   └── pom.xml            # Maven configuration
│
└── frontend/
    ├── src/               # ReactJS components and views
    └── public/            # Static assets

```

---

## API Endpoints

### Authentication

- **Login**: `POST /api/users/login`
  - Request Body: `{ "username": "user", "password": "pass" }`
  - Response: `{ "jwtToken": "...", "username": "...", "roles": [...] }`

- **Register**: `POST /api/users/register`
  - Request Body: `{ "username": "user", "password": "pass" }`

- **Get Current User**: `GET /api/users/me`
  - Requires JWT token in headers: `Authorization: Bearer <token>`

---

## Contributing

We welcome contributions! Please fork the repository and create a pull request with your changes.

---

## License

This project is licensed under the MIT License.

---

### Tags & Keywords:
```
finance-tracker, personal-finance, spring-boot, reactjs, JWT, API
```