## SpringTraining


## Creating build using local

### Create docker build if using local 
```
docker build -t spring-training .
```

In case it fails due to maven goals, optionally you can either use powershell or fix the error
OR comment Dockerfile maven goal <b>#RUN mvnw -DskipTests package </b>

Run it manually
```
mvnw -DskipTests package
docker build -t spring-training .
```

Make sure you have same jdk version(11) when packing mvn at local and Dockerfile jdk version (11)
 
### Tag build with gcr.io
```
docker tag spring-training  gcr.io/hht-uat-206210/spring-training
```

### Deploy build on GCR
```
docker push gcr.io/hht-uat-206210/spring-training
```

Deploy latest build from gcr.io (container registry) to your GKE cluster using console or gcloud, kubectl -- see GCP docuemntation

### Use GKE ConfigMap to store database credentials
1. Create database credentails as mentioned in application.properties file on ConfigMap
2. Retrieve them in deplayment.yaml as environment variables
3. Example, in application.properties
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/j2ee_training
     create a key value in config map like 
       SPRING_DATASOURCE_URL=jdbc:mysql://<cloudsql-private-ip>:3306/j2ee_training
   ```
   Use SPRING_DATASOURCE_URL as environment variable in deployment.yaml when deploying this build on GKE
   ```
   containers:
      - env:
        - name: SPRING_DATASOURCE_URL
          valueFrom:
            configMapKeyRef:
              key: SPRING_DATASOURCE_URL
              name: spring-training
   ```
   spring-training -- ConfigMap name where we have store the credentails. 
   
   
  
