#FROM openjdk:8-jdk
FROM java:openjdk-9

RUN apt-get update && apt-get install -y netcat

ENV JAVA_CONF_DIR=$JAVA_HOME/conf

# workarround for missisnh config folder. could be debian related problems
RUN bash -c '([[ ! -d $JAVA_SECURITY_DIR ]] && ln -s $JAVA_HOME/lib $JAVA_HOME/conf) || (echo "Found java conf dir, package has been fixed, remove this hack"; exit -1)'


ENTRYPOINT ["/mnt/contribution/waitForMysqlAndStart.sh"]

EXPOSE 8080
