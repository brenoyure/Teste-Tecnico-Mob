FROM quay.io/wildfly/wildfly:33.0.2.Final-jdk21

WORKDIR /opt/jboss/wildfly/

EXPOSE 8080

ARG EVENTOS_DB_HOST=${EVENTOS_DB_HOST}
ARG EVENTOS_DB_NAME=${EVENTOS_DB_NAME}
ARG EVENTOS_DB_USER=${EVENTOS_DB_USER}
ARG EVENTOS_DB_PASSWORD=${EVENTOS_DB_PASSWORD}

COPY ./postgresql-jdbc-driver.jar /tmp/postgresql-jdbc-driver.jar
COPY ./config.cli /tmp/config.cli

RUN ["/opt/jboss/wildfly/bin/jboss-cli.sh", "--file=/tmp/config.cli", "--resolve-parameter-values"]

COPY ./target/*.war ./standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "--server-config=standalone.xml", "-b" ,"0.0.0.0"]
