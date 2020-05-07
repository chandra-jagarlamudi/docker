# Todo Web Application using Spring Boot and MySQL as Database

Run com.ragas.springboot.web.SpringBootFirstWebApplication as a Java Application.

Runs on default port of Spring Boot - 8080

Application uses h2 database to run the tests.


## Can be run as a Jar or a WAR

`mvn clean install` generate a war which can deployed to your favorite web server.

We will deploy to Cloud as a WAR

## Web Application

- http://localhost:8080/login with ragas/password as credentials
- You can add, delete and update your todos
- Spring Security is used to secure the application
- `com.ragas.springboot.web.security.SecurityConfiguration` contains the in memory security credential configuration.

## My SQL

### Launching MySQL using Docker

```
docker run --detach --env MYSQL_ROOT_PASSWORD=rootpassword --env MYSQL_USER=todosuser --env MYSQL_PASSWORD=todospassword --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:5.7
```

### Launching Web App using Docker

Using Link

```
docker container run -p 8080:8080 --link=mysql -e RDS_HOSTNAME=mysql  cjragas/todo-web-app-mysql:0.0.1-SNAPSHOT
```

### Launching App and MySql with Docker Compose

Instead of running the App and MySql seperately we can use docker-compose command to run them with one command

```
docker-compose up
```