FROM adoptopenjdk/openjdk11:alpine-jre
COPY ./target/bank-0.0.1-SNAPSHOT.jar /opt/bank.jar
WORKDIR /opt    
EXPOSE 8081
CMD ["java", "-jar", "bank.jar"]