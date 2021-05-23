FROM openjdk:8u171-jre-alpine
RUN apk --no-cache add curl
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD java ${JAVA_OPTS} -jar evaluation.jar
ENTRYPOINT ["java","-jar","/app.jar"]
HEALTHCHECK --start-period=30s --interval=5s CMD curl http://localhost:8888/actuator/health || exit 1