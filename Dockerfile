FROM gradle:7.6.2-jdk17 as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM openjdk:17
WORKDIR /app
EXPOSE 9011
COPY --from=builder /builder/build/libs/basic-rest-1.0.0.jar .
CMD ["java", "-jar", "basic-rest-1.0.0.jar"]