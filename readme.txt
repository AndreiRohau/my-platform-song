Run docker-compose up





GET audio bin
http://localhost:8081/v1/api/resources/1

POST save audio and meta
http://localhost:8081/v1/api/resources/
Content-Type audio/mpeg
file -> of.mp3

DELETE audio bin
http://localhost:8081/v1/api/resources?id=0,1,2



GET song meta
http://localhost:8082/v1/api/songs/0

DELETE song meta
http://localhost:8082/v1/api/songs?id=0,1,2