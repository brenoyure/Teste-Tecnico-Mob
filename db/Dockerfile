FROM postgres:alpine

EXPOSE 5432

COPY ./secrets/* /run/secrets/

ENV POSTGRES_DB_FILE=/run/secrets/postgres-db
ENV POSTGRES_USER_FILE=/run/secrets/postgres-user
ENV POSTGRES_PASSWORD_FILE=/run/secrets/postgres-passwd
