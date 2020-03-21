# Docker data

#### Start MySql Container (downloads image if not found)
```
docker run --detach --name quarkus-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=quarkus_data -e MYSQL_USER=admin -e MYSQL_PASSWORD=admin -d mysql
```
##### view all images
```
docker images
```
##### view all containers (running or not)
```
docker ps -a
```
##### Interact with Database (link to quarkus-mysql container) with mysql client
```
docker run -it --link quarkus-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
```
##### SQL commands
```
mysql>  
show databases;  
use quarkus_data;  
show tables;  
quit
```
##### Stop quarkus-mysql container
```
docker stop quarkus-mysql
```
##### (Re)Start quarkus-mysql container
```
docker start quarkus-mysql
```
##### Remove quarkus-mysql container (must stop it first)
```
docker rm quarkus-mysql
```
##### Remove image (must stop and remove container first)
```
docker rmi mysql:latest
```
