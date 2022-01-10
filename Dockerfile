FROM openjdk:11.0.13-slim
COPY "./target/backend-basic-challenge-0.0.1-SNAPSHOT.jar" "bcp-challenge.jar"
EXPOSE 9091
CMD ["java", "-jar", "./bcp-challenge.jar"]