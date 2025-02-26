# URL Shortener (Bit.ly Clone)

## Overview
This project is a simple URL shortener built using **Spring Boot** with **PostgreSQL** and **Redis** for caching. It provides REST APIs to shorten URLs and retrieve original URLs efficiently.

## Features
✅ **Shorten URLs** using Base64 encoding
✅ **Retrieve original URL** via short URL
✅ **Redis caching** for fast lookup
✅ **Dockerized PostgreSQL & Redis** for easy setup
✅ **Spring Boot REST API** implementation

---
## Tech Stack
- **Backend:** Java (Spring Boot)
- **Database:** PostgreSQL
- **Caching:** Redis
- **Tools:** Docker, IntelliJ IDEA, Postman

---
## Setup & Installation

### 1️⃣ Prerequisites
Make sure you have installed:
- **Java 17+** (`java --version` to check)
- **Maven** (`mvn -version` to check)
- **Docker** (`docker --version` to check)

### 2️⃣ Clone Repository
```sh
git clone https://github.com/your-repo/url-shortener.git
cd url-shortener
```

### 3️⃣ Start PostgreSQL in Docker
```sh
docker run --name postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=shortener \
  -p 5432:5432 \
  -d postgres
```
Check if it's running:
```sh
docker ps
```

### 4️⃣ Start Redis in Docker
```sh
docker run --name redis -p 6379:6379 -d redis
```
Test Redis:
```sh
redis-cli ping
```
Expected output: `PONG`

### 5️⃣ Configure Spring Boot (application.properties)
Edit `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/shortener
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.redis.host=localhost
spring.redis.port=6379
```

### 6️⃣ Build & Run Application
```sh
mvn clean install
mvn spring-boot:run
```

---
## API Endpoints

### 🔹 **Shorten URL**
**POST** `/api/url/shorten`
```json
{
  "longUrl": "https://example.com"
}
```
**Response:**
```json
"a1b2c3"
```

### 🔹 **Get Original URL**
**GET** `/api/url/{shortUrl}`
```sh
curl -X GET http://localhost:8080/api/url/a1b2c3
```
**Response:**
```json
"https://example.com"
```

---
## Redis Caching
- First request fetches from **PostgreSQL**
- Second request fetches from **Redis** (faster!)
- Cached URLs expire after **1 day**

To check Redis manually:
```sh
redis-cli get a1b2c3
```

---
## Contributors
👤 Keshav Chaudhary

