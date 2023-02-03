FROM openjdk:8-jre-alpine
LABEL maintainer="Sandeep Kumar"
WORKDIR /
ARG VER=0.0.1
ENV VER ${VER}
ADD ./target/CustomerService-$VER.jar /opt/lib/CustomerService.jar
EXPOSE 80
ENTRYPOINT ["java", "-Xmx512m","-Xss16m","-jar", "-Dconsole.level=INFO", "/opt/lib/CustomerService.jar"]