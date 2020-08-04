# Demo Application

A Springboot application which helps to showcase the Projects developed.

Idea is to also learn about different featuress of Springboot

### Rest Controller to test handling of different type of HTTP Requests

HTTPRequestTesterController 

### A sample controller to expose a model resource.

ProjectRestController

### Added Spring Security to secure the web application.

User information is validated against the information stored in a database.

Password is hashed with bcrypt and stored in the database.
https://en.wikipedia.org/wiki/Bcrypt

WebSecurityConfig maintains the security configuration of the application.

1. There is a public endpoint which is not protected.
2. Other endpoints are protected using basic and form authentication



