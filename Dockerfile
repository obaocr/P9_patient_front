FROM openjdk:8-jdk-alpine
LABEL responsable="o.barberis@outlook.fr"
EXPOSE 8047:8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} patientFront.jar
ENTRYPOINT ["java","-jar","/patientFront.jar"]