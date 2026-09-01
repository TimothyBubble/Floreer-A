# Floreer-A
A plant monitoring backend built to learn and help maintain my plants.
It's covering physical hardware to a production-style REST API.

A capacitive soil moisture sensor, wired to an Arduino Uno, is calibrated and read by a Raspberry Pi Zero 2 W running Python. 
The Pi sends readings over the network to a Spring Boot backend, which validates incoming data, saves it to PostgreSQL via Hibernate/JPA, and applies business logic (example: detect when a plant needs watering).
A scheduled background job automatically prunes old data. Postgres runs in Docker. The project is structured to move toward full containerization and CI/CD next.

<b> Stack: </b> Java, Spring Boot, Hibernate/JPA, PostgreSQL, Docker, Python, Arduino/Raspberry Pi