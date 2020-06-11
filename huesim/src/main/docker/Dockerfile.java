FROM openjdk:8-jdk-alpine
        EXPOSE 8088
        ARG JAR_FILE=huesim/target/*.jar
COPY ${JAR_FILE} SpringDemoApp.jar
ENTRYPOINT ["java","-jar","/SpringDemoApp.jar"]