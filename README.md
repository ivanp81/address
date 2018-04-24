# Address microservice

This is a proof-of-concept application which demonstrates, against a simple Spring Boot application, the following: 

- containerization through Docker
- testing strategies
- continuous integration thorough a Jenkins server using the Kubernetes plugin
- execution inside Kubernetes

# Testing

Following are the assumption made for testing:

- Unit tests perform both, behaviour (with a BDD style) and state verification
- Some test duplication are possible as we want see the testing strategies from different point of view

#### Framework

- Junit
- Mockito
- RestAssured
- Spring Test
- WireMock (for external service stubbing)
- Pact (for Consumer Driven Contract Test)

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
$ docker build --tag IMAGE_NAME .
```

## How to run
 
```
$ docker container run --name CONTAINER_NAME -p 8080:8080 IMAGE_NAME
```
## Pipeline
You can find the code for the pipeline in the following repository:

- https://github.com/ivanp81/address-pipeline