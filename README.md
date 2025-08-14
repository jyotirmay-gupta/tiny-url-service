# Tiny URL

**Copyright (c) 2025 Jyotirmay Gupta**

[![Build Status](https://github.com/jyotirmay-gupta/tiny-url-service/actions/workflows/maven.yml/badge.svg)](https://github.com/jyotirmay-gupta/tiny-url-service/actions/workflows/maven.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-brightgreen.svg)](LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/jyotirmay-gupta/tiny-url-service.svg)](https://github.com/jyotirmay-gupta/tiny-url-service/stargazers)
[![GitHub last commit](https://img.shields.io/github/last-commit/jyotirmay-gupta/tiny-url-service.svg)](https://github.com/jyotirmay-gupta/tiny-url-service/commits)
[![Contributors](https://img.shields.io/github/contributors/jyotirmay-gupta/tiny-url-service.svg)](https://github.com/jyotirmay-gupta/tiny-url-service/graphs/contributors)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-v3.5.3-brightgreen)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-brightgreen)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14.5-brightgreen)](https://www.postgresql.org/)
[![codecov](https://codecov.io/github/jyotirmay-gupta/tiny-url-service/graph/badge.svg?token=HFY86G033B)](https://codecov.io/github/jyotirmay-gupta/tiny-url-service)


**Project:** Tiny URL  
**Description:** This is a personal project by Jyotirmay Gupta that implements a simplified URL shortening service, similar in concept to services like bit.ly or tinyurl.com. It allows long URLs to be converted into compact, easy-to-share short URLs.

This code is intended for educational and personal use, demonstrating core backend concepts such as encoding algorithms, database storage, and URL mapping.

Licensed under the Apache License Version 2.0. See LICENSE file for more details.

---

## Overview

This Spring Boot project provides a REST API for shortening URLs, updating them, retrieving original URLs, and deleting shortened URLs.

The API supports versioning via the `Accept-Version` header, allowing for future backward-compatible changes.

---

## API Endpoints

All endpoints **require the header:**  
`Accept-Version: v1`

### Create Tiny URL

- **Endpoint:** `POST /tiny/url`
- **Consumes:** `application/json`
- **Produces:** `application/json`
- **Request Body:** `CreateTinyUrlRequestTO` (JSON)
- **Response:** `TinyUrlResponseTO`

Creates a shortened URL for a given original URL.

---

### Update Tiny URL

- **Endpoint:** `PUT /tiny/url`
- **Consumes:** `application/json`
- **Produces:** `application/json`
- **Request Body:** `UpdateOriginalURLRequestTO` (JSON)
- **Response:** `TinyUrlResponseTO`

Updates information related to an existing tiny URL.

---

### Get Original URL

- **Endpoint:** `GET /tiny/url/{tinyUrl}`
- **Produces:** `application/json`
- **Path Variable:** `tinyUrl` (the shortened URL code)
- **Response:** `TinyUrlResponseTO`

Retrieves the original URL for a given tiny URL code.

---

### Delete Tiny URL

- **Endpoint:** `DELETE /tiny/url/{tinyUrl}`
- **Produces:** `application/json`
- **Path Variable:** `tinyUrl` (the shortened URL code)
- **Response:** `TinyUrlResponseTO`

Deletes the tiny URL entry.

---

## Technologies Used

- Java 21
- Spring Boot
- Spring Web (REST API)
- JPA / Hibernate (for persistence)
- PostgreSQL (or other relational database)

---

## How to Run

1. Clone the repository
2. Configure your database settings in `application.yml`
3. Build the project with Maven
4. Run the Spring Boot application
5. Use tools like Postman or curl to interact with the API

---

## Notes

- The `TinyUrlController` lazily injects the `TinyUrlService` bean.
- API versioning is handled through the custom `Accept-Version` header for future-proofing API changes.

---

## License

Licensed under the Apache License Version 2.0. See the LICENSE file for details.

---

*Created by Jyotirmay Gupta — 2025*