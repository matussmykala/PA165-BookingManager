#!/bin/bash
# init

GREEN='\033[0;32m'
YELLOW='\033[1;33m'
ORANGE='\033[0;33m'
NC='\033[0m'

function pause(){
	printf "\n\n${YELLOW}Press [Enter] to continue...${NC}\n\n" && read
}

printf "${GREEN}To show all rooms${NC}\n"
printf "${ORANGE}\ncurl -i -X GET http://localhost:8080/pa165/rest/room\n\n${NC}"
    curl -i -X GET http://localhost:8080/pa165/rest/room

pause

printf "${GREEN}To get room with id (in our example, id=1)${NC}\n"
printf "${ORANGE}\ncurl -i -X GET http://localhost:8080/pa165/rest/room/1\n\n${NC}"    
    curl -i -X GET http://localhost:8080/pa165/rest/room/1

pause

printf "${GREEN}To delete room with id (in our example, id =1)${NC}\n"
printf "${ORANGE}\ncurl -i -X DELETE http://localhost:8080/pa165/rest/room/1\n\n${NC}"
    curl -i -X DELETE http://localhost:8080/pa165/rest/room/1

pause

printf "${GREEN}Check that room is gone${NC}\n"
printf "${ORANGE}\ncurl -i -X GET http://localhost:8080/pa165/rest/room/1\n\n${NC}"
    curl -i -X GET http://localhost:8080/pa165/rest/room/1

pause

printf "${GREEN}To create room(name, numberOfBeds, price, hotel, reservations) (in out example name = TEST, numberOfBeds = 2, price = 25.20, hotel = getHotel(id=2), reservations = [])${NC}\n"
printf "${ORANGE}\ncurl -X POST -i -H "Content-Type: application/json" --data '{"name":"TEST","numberOfBeds":2,"price":25.20,"hotel":{"id":2,"name":"Park Hotel","address":"Praha","description":"Pekny hotel v Prahe","lastUpdateDay":"2016-07-27 00:00"},"reservations":[]}' http://localhost:8080/pa165/rest/room/create
\n\n${NC}"
    curl -X POST -i -H "Content-Type: application/json" --data '{"name":"TEST","numberOfBeds":2,"price":25.20,"hotel":{"id":2,"name":"Park Hotel","address":"Praha","description":"Pekny hotel v Prahe","lastUpdateDay":"2016-07-27 00:00"},"reservations":[]}' http://localhost:8080/pa165/rest/room/create

pause

printf "${GREEN}Check that room was really created${NC}\n"
printf "${ORANGE}\ncurl -i -X GET http://localhost:8080/pa165/rest/room/6\n${NC}" 
    curl -i -X GET http://localhost:8080/pa165/rest/room/6
pause
