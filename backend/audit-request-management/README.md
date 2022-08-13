# Added Initial Setup:

 ## Dependencies Added:
- Lombok 
- MySql Driver 
- Spring Data Jpa 
- Spring Web 
- Flyway Core 
- Flyway Mysql 
- Log4J2
- Keycloak Adapter
- Keycloak Starter
- H2 Database
- Dozen Mapper


 ## Flyway Integration:
- Flyway is an open-source database migration tool. It strongly favors simplicity and convention over configuration.It is based around just 7 basic commands: Migrate, Clean, Info, Validate, Undo, Baseline and Repair.
. Flyway is an open-source database migration tool. It strongly favors simplicity and convention over configuration.It is based around just 7 basic commands: Migrate, Clean, Info, Validate, Undo, Baseline and Repair.
 the classpath of the directory is db/migration

 ## Run Keycloak with persisting data:

Run Keycloak using Docker compose file
```
    docker-compose -f docker-compose-keycloak.yml up -d 
```
- Keycloak will be available on http://localhost:7688/
- Create a realm `myrealm` and client `myclient`
- Create a user and add necessary roles
- [Reference](https://medium.com/@max.mayr/keycloak-and-spring-boot-security-b069306b0fb0)

 ## Run MySQL Database with Spring-boot Application:
Run MYSQL using Docker compose file
```
    docker-compose -f docker-compose.yml up -d 
```
- [Reference](https://github.com/bezkoder/docker-compose-spring-boot-mysql)
