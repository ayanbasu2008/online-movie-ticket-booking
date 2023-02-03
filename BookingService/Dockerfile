BookingService openjdk:8-jre-alpine
LABEL maintainer="Sandeep Kumar"
WORKDIR /
ARG VER=0.0.1
ENV VER ${VER}
ADD ./target/BookingService-$VER.jar /opt/lib/BookingService.jar
EXPOSE 80
ENTRYPOINT ["java", "-Xmx512m","-Xss16m","-jar", "-Dconsole.level=INFO", "/opt/lib/BookingService.jar"]