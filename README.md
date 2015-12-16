# PA165-BookingManager

Project for pa165.

## Travis CI - Build status 
[![Build Status](https://travis-ci.org/matussmykala/PA165-BookingManager.svg?branch=master)](https://travis-ci.org/matussmykala/PA165-BookingManager)

## Requirements

- Framework - Spring
- Bootstrap
- Apache Tomcat
- Google Chrome web browser (Check of browser is provided by web interface)

 
## Modules

- api-layer	- API for whole app
- dao-layer	- Data layer
- service-layer - Service layer
- rest-layer - REST service
- user-layer - User web interface
- web-sample-data - Sample Data 

## Installation and run

Use maven and startup-web.sh script for web interface and startup-rest.sh script for REST interface

mvn clean install

mvn tomcat7:run


## Web interface using

 http://localhost:8080/pa165
 
## REST interface using

 http://localhost:8080/pa165/rest

## Repository

git clone https://github.com/matussmykala/PA165-BookingManager.git

