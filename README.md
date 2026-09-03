# Floreer-A
A plant monitoring backend built to learn and help maintain my plants.
It's covering physical hardware to a production-style REST API.

03-09
A capacitive soil moisture sensor, wired to an Arduino Uno, is calibrated and read by a Raspberry Pi Zero 2 W running Python. 
The Pi sends readings over the network to a Spring Boot backend, which validates incoming data, saves it to PostgreSQL via Hibernate/JPA, and applies business logic (example: detect when a plant needs watering).
A scheduled background job automatically cleans old data. Postgres runs in Docker. The project is structured to move toward containerization and CI/CD.

<b> Stack: </b> Java, Spring Boot, Hibernate/JPA, PostgreSQL, Docker, Python, Arduino/Raspberry Pi
