# Address microservice

This is a proof-of-concept application which demonstrates, against a simple Spring Boot application, the following: 

- application containerization through Docker
- application execution inside Kubernetes
- testing strategies
- continuous integration thorough a Jenkins server using the Kubernetes plugin

## Run the test

#### Unit test  
```
$ mvn test
```

#### Integration\Component test
```
$ mvn verify
```
#### Acceptance test
```
$ mvn verify -Pacceptance-tests -Dacceptance.address.url=URL_TO_ADDRESS_TESTING_ENVIRONMENT
```

## Build the project

#### Build executable application	
```
$ mvn clean package
```

#### Build Docker images
```
$ docker... 
```

## How to run
 
```
$ docker...
```
## Pipeline
You can find the code for the pipeline in the following repository:

- https://github.com/ivanp81/address-pipeline