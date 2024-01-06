FROM --platform=linux/amd64 gradle:8.5.0-jdk17 AS builder

ARG GH_USR
ARG GH_PWD
ARG VERSION

ENV GITHUB_ACTOR=$GH_USR
ENV GITHUB_TOKEN=$GH_PWD

WORKDIR /build

COPY adapter/build.gradle ./adapter/build.gradle
COPY application/build.gradle ./application/build.gradle
COPY drivers/mysql/build.gradle ./drivers/mysql/build.gradle
COPY drivers/rest/build.gradle ./drivers/rest/build.gradle
COPY enterprise/build.gradle ./enterprise/build.gradle
COPY launcher/build.gradle ./launcher/build.gradle
COPY build.gradle .
COPY settings.gradle .

RUN gradle build --no-daemon > /dev/null 2>&1 || true

COPY . .

RUN gradle build -x test --no-daemon


FROM --platform=linux/amd64 openjdk:17-alpine

WORKDIR /service

COPY --from=builder /build/launcher/libs/customer-service.jar ./customer-service.jar

RUN /bin/sh -c 'touch /service/customer-service.jar'

CMD ["java", "-jar", "customer-service.jar"]