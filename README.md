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

 No admin

     Customer_1

 	user name: hraska@seznam.cz
 	pasword: pass1

     Customer_2

	user name: hraska2@seznam.cz
	pasword: pass2
 
 Admin

     Customer_3

	user name: adamkova@seznam.cz
	pasword: pass3

     Customer_4

	user name: kubova@seznam.cz
	pasword: pass4 
 
## REST interface using

 http://localhost:8080/pa165/rest

For testing the REST interface use commands from command line

	1. To show all rooms
		curl -i -X GET http://localhost:8080/pa165/rest/room

	2. To get room with id (in our example, id=1)
		curl -i -X GET http://localhost:8080/pa165/rest/room/1

	3. To delete room with id (in our example, id =1)
		curl -i -X DELETE http://localhost:8080/pa165/rest/room/1
	   -> you can check that room is gone by using command
		curl -i -X GET http://localhost:8080/pa165/rest/room/1
	      with result "Not found"

	4. To create room(name, numberOfBeds, price, hotel, reservations)
	   (in out example name = TEST, numberOfBeds = 2, price = 25.20, hotel = getHotel(id=2), reservations = [])
		curl -X POST -i -H "Content-Type: application/json" --data'{"name":"TEST","numberOfBeds":2,"price":25.20,"hotel":{"id":2,"name":"Park Hotel","address":"Praha","description":"Pekny hotel v Prahe","lastUpdateDay":"2016-07-27 00:00"},"reservations":[]}' http://localhost:8080/pa165/rest/room/create
	    -> you can check that room was really created by using command
		curl -i -X GET http://localhost:8080/pa165/rest/room/6

## Repository

git clone https://github.com/matussmykala/PA165-BookingManager.git

