# Docker data
#### Start Elasticsearch Container (downloads image if not found)
```
docker run -it --rm=true --name elasticsearch_quarkus_test ^
-p 9200:9200 -p 9300:9300 ^
-e "discovery.type=single-node" ^
docker.elastic.co/elasticsearch/elasticsearch:7.5.0
```
#### Start Database Container
##### MySql
```
docker run --detach --name quarkus-mysql ^
-p 6604:3306 ^
-e MYSQL_ROOT_PASSWORD=root ^
-e MYSQL_DATABASE=quarkus_data ^
-e MYSQL_USER=admin -e MYSQL_PASSWORD=admin ^
-d mysql
```
##### PostgreSQL
```
docker run --detach --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 ^
--name postgresql_quarkus_test ^
-e POSTGRES_USER=quarkus_test ^
-e POSTGRES_PASSWORD=quarkus_test ^
-e POSTGRES_DB=quarkus_test ^
-p 5432:5432 postgres:11.3
```
##### MongoDB
```
docker run --detach -ti --rm -p 27017:27017 mongo:4.0
```
##### Neo4j
```
docker run --detach --publish=7474:7474 --publish=7687:7687 -e 'NEO4J_AUTH=neo4j/secret' neo4j:4.0.0
```
##### view all images
```
docker images
```
##### view all containers (running or not)
```
docker ps -a
```
##### Interact with MySQL Database (link to quarkus-mysql container) in PowerShell
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
