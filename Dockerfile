#docker build
FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
#RUN mvnw -DskipTests package
COPY target/spring-training-0.0.1-SNAPSHOT.jar /opt/app/spring-training.jar
EXPOSE 80/tcp
ENTRYPOINT ["java", "-jar", "/opt/app/spring-training.jar"]