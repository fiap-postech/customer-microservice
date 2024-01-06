FROM --platform=linux/amd64 openjdk:17-alpine

WORKDIR /service

COPY ./assets/customer-service.jar ./customer-service.jar

RUN /bin/sh -c 'touch /service/customer-service.jar'

CMD ["java", "-jar", "customer-service.jar"]