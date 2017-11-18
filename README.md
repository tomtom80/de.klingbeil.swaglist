#  SwagList Demonstration Application

The SwagList App is a demonstration application comprised of: 

* Java REST application written using Spring-Boot, 
* a React fontend,
* a NGINX reverse proxy implementing https,

# Requirements

This example uses features in Docker 17.05 CE Edge. Install this version to run the example.

## Secrets

This application uses Docker secrets to secure the application components. The reverse proxy requires creating a certificate that is stored as a secret. To create a certificate and add as a secret:

```
mkdir certs

openssl req -newkey rsa:4096 -nodes -sha256 -keyout certs/domain.key -x509 -days 365 -out certs/domain.crt

docker secret create revprox_cert certs/domain.crt

docker secret create revprox_key certs/domain.key

```

# Building and Running the SwagList

## Run as an application

To run the SwagList App as an application:
```
docker-compose up --build
```

## Deploy to a swarm
```
#If you need to create a Swarm
docker swarm init
docker stack deploy -c docker-stack.yml swaglist
```

## A simplified development environment
This compose file creates a simplified development environment consisting of only the application server.

```
docker-compose --file docker-compose-dev.yml up --build
```



## The SwagList App

The URL for the content is `http://localhost:8080/`

