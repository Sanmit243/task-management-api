# Task Management REST API

A REST API built with Spring Boot, PostgreSQL, and JWT Authentication.

## Tech Stack
- Java 17+
- Spring Boot 4.1.0
- PostgreSQL
- Maven

---

## Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL installed and running locally

---

## Configuration

Set these environment variables:

| Variable | Description | Example |
|---|---|---|
| `DB_URL` | PostgreSQL JDBC URL | `jdbc:postgresql://localhost:5432/taskdb` |
| `DB_USERNAME` | PostgreSQL username | `postgres` |
| `DB_PASSWORD` | PostgreSQL password | `yourpassword` |
| `JWT_SECRET_KEY` | JWT signing key (min 32 chars) | `my-super-secret-key-change-this` |

> Generate a strong JWT secret: `openssl rand -base64 32`

---

## Run Locally

**1. Create a database**
```sql
CREATE DATABASE taskdb;
```

**2. Set environment variables**

Windows:
```cmd
set DB_URL=jdbc:postgresql://localhost:5432/taskdb
set DB_USERNAME=postgres
set DB_PASSWORD=yourpassword
set JWT_SECRET_KEY=my-super-secret-key-change-this
```

Mac/Linux:
```bash
export DB_URL=jdbc:postgresql://localhost:5432/taskdb
export DB_USERNAME=postgres
export DB_PASSWORD=yourpassword
export JWT_SECRET_KEY=my-super-secret-key-change-this
```

**3. Run**
```bash
mvn spring-boot:run
```
App starts on `http://localhost:8080`

> Tables are created automatically on first run.

---

## API Endpoints

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| POST | `/api/auth/register` | No | Register |
| POST | `/api/auth/login` | No | Login → returns JWT token |
| GET | `/api/tasks` | Yes | Get all tasks |
| GET | `/api/tasks/{id}` | Yes | Get task by ID |
| POST | `/api/tasks` | Yes | Create task |
| PUT | `/api/tasks/{id}` | Yes | Update task |
| DELETE | `/api/tasks/{id}` | Yes | Delete task |

> Pass token in header: `Authorization: Bearer <token>`
