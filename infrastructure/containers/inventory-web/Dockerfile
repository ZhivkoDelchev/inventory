FROM openjdk:11-jre-slim

RUN apt-get update && apt-get install -y netcat

ENTRYPOINT ["/mnt/contribution/waitForMysqlAndStart.sh"]

EXPOSE 8080
