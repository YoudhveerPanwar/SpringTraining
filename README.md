# SpringTraining


# Creating build using local

# Create docker build if using local 
docker build -t spring-training .

In case it fails due to maven goals, optionally you can either use powershell or fix the error
OR comment Dockerfile maven goal #RUN mvnw -DskipTests package

Run it manually
mvnw -DskipTests package
docker build -t spring-training .
Make sure you have same jdk version(11) when packing mvn at local and Dockerfile jdk version (11)
 
# Tag build with gcr.io
docker tag spring-training  gcr.io/hht-uat-206210/spring-training

# Deploy build on GCR
docker push gcr.io/hht-uat-206210/spring-training

# Deploy latest build from gcr.io (container registry) to your GKE cluster using console or gcloud, kubectl -- see GCP docuemntation