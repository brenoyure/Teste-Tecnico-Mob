#!/bin/bash
docker compose down
cd ./app
mvn clean package
cd ..
docker compose build
docker compose up -d

