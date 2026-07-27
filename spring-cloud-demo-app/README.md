# demo-app

HAWK Demo tenant demo-app pplication for testing and showcasing.

### Build
```
./mvnw clean install -s .mvn/wrapper/settings.xml
java -jar target/*.jar
```

### Build, run test and push Docker container
```
docker build -t cbi-hawk-demo-docker-local.artifactory.alm.corp.hmrc.gov.uk/demo-app:0.0.1 .
docker run -p 8080:8080 cbi-hawk-demo-docker-local.artifactory.alm.corp.hmrc.gov.uk/demo-app:0.0.1

// Test
curl localhost:8080/actuator | jq .

// Login to registry
docker login cbi-hawk-demo-docker-local.artifactory.alm.corp.hmrc.gov.uk

// Push
docker push cbi-hawk-demo-docker-local.artifactory.alm.corp.hmrc.gov.uk/demo-app:0.0.1
```
## Web UI
A web UI has now been added, this can be reached at http://[host]:[port]/ e.g. http://localhost:8080/

## Endpoints

### Repository
```
// Get a Tenant name using its ID
GET http://localhost:8080/rds/mysql/{Tenant ID}

// Add a Tenant to database
GET http://localhost:8080/rds/mysql/add/{Tenant Name}
```

### RabbitMQ
```
// Push a message to the queue
GET http://localhost:8080/messaging/rabbitmq/send/{message}

// Get last message received
GET http://localhost:8080/messaging/rabbitmq/receive
```


