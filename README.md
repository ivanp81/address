# address microservice

This is a proof-of-concept application which demonstrates, against a simple Spring Boot application, the following: 

- component containerization through Docker
- various type of test coming from the test pyramid
- perform test through a Jenkins Pipeline 

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
The pipeline is run by a Jenkins Server execute as a Pod inside Kubernetes. It uses the Kubernetes Plugin. You can find the code for the pipeline in the following repository:

- https://github.com/ivanp81/address-pipeline