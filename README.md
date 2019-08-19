# basic-vaadin-crud


## Description

A basic Vaadin frontend that support CRUD with minimal interface connected to a H2 database backend. 

Screen shot : 

![UI](https://github.com/duongtuanhiep/basic-vaadin-crud/blob/master/tesstrun02.png)
![UI](https://github.com/duongtuanhiep/basic-vaadin-crud/blob/master/testrun01.png)


## Fucntionality

This application consist of 2 tables that user can modify(add/update/delete) the content of each tables and the 

## Installation 

This project is a spring boot project using maven. You can clone this project to your local computer andd import into your local Spring Tool Suite as maven project. 


## Usage 

You can start this application in any IDE that has JDK or JRE or alternatively by using command line. Compiled and run this program as a java program.

The database used in this application is the H2 database which is a in-memory database that should be used for testing purpose only. You can hook an actual mySQL database to the application by modifying the file "application.properties" located in \src\main\resources\ . Please be aware that there may be some error coming from the fact that the application let Hibernate create the tables automatically and Hibernate may behave differently when using with different databases. Currently the file is configured as : 
```
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:hiepbodoi
```

The 
