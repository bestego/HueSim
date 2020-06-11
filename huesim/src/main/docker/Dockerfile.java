FROM java:8-jre-alpine

EXPOSE 8080

RUN mkdir /app
COPY target/ /app/identitygateway.jar

ENTRYPOINT ["java","/app/identitygateway.jar/opensignum-identity-gateway-1.0-SNAPSHOT.jar"]