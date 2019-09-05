# basic-vaadin-crud


## Description

A basic Vaadin frontend that support CRUD with minimal interface connected to a H2 database backend. 
This application consist of 2 tables that user can modify(add/update/delete) the content of each tables as well as filter the information with any combinations. 

Screen shot : 

![UI](https://github.com/duongtuanhiep/basic-vaadin-crud/blob/master/tesstrun02.png)
![UI](https://github.com/duongtuanhiep/basic-vaadin-crud/blob/master/testrun01.png)

## Built with 

This application was built using : 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring web mvc](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
- [Vaadin](https://vaadin.com/)



## Installation 

This project is a spring boot project using maven. You can download this project to your local computer andd import into your local Spring Tool Suite as maven project. 


## Usage 

You can start this application in any IDE that has JDK or JRE or alternatively by using command line. Compiled and run this program as a java program.

The database used in this application is the H2 database which is a in-memory database that should be used for testing purpose only. You can hook an actual mySQL database to the application by modifying the file "application.properties" located in \src\main\resources\ . Please be aware that there may be some error coming from the fact that the application let Hibernate create the tables automatically and Hibernate may behave differently when using with different databases. Currently the file is configured as : 
```
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:hiepbodoi
```

If you wish to use the H2 database, you can also access H2 console to inspect or modify the tables through : 
```
http://localhost:8080/h2-console/
```
![console](https://github.com/duongtuanhiep/basic-vaadin-crud/blob/master/dbconsole.png)

The username and password by default is : 
```
username : sa
password :
NOTE : password is empty 
```


The data added to the database is read from the file "data.sql" located in \src\main\resources\. When the application start up, after the table is created, the application will read the file and execute all the script. You can modify or delete this file if you already have an existing database. A sample of the current data.sql script file : 
```
insert into TEAM values (2,'TESTDATA2','TESTDATA02') ;
insert into TEAM values (3,'TESTDATA3','TESTDATA03') ;
insert into EMPLOYEE values ('test01',1,'testmail1@gmail.com','test01','test01',1);
insert into EMPLOYEE values ('test02',0,'testmail2@gmail.com','test02','test02',1);
```

## Contributing & author

Duong Tuan Hiep - Duongtuanhiep18398@gmail.com
