Country Proxy API

A lightweight Spring Boot REST API that acts as a clean façade over the public REST Countries service. No database, no API key, no infrastructure — just one stateless endpoint that returns well-shaped country data.


Overview

The application exposes a single REST endpoint that fetches country information from a public upstream API and returns a simplified, stable JSON contract. This decouples your consumers from the upstream API's quirks, field name changes, and versioning issues.

Key characteristics:

Stateless — no database, no schema, no migrations

No authentication or API key required

Clean DTO contract independent of the upstream JSON shape

Proper error handling (404 for unknown countries, 502 for upstream failures)


Tech Stack
Component	Version
Java	121
Spring Boot	4.2
Spring Web (MVC)	via starter
Spring REST Client	via spring-boot-starter-restclient
Maven	3.8+

Project Structure

country-proxy/
├── pom.xml
└── src/main/
    ├── java/com/afro/country/proxy/
    │   ├── CountryProxyApplication.java
    │   ├── config/RestClientConfig.java
    │   ├── controller/CountryController.java
    │   ├── service/CountryService.java
    │   ├── dto/CountryResponse.java
    │   └── exception/GlobalExceptionHandler.java
    └── resources/
        └── application.properties



