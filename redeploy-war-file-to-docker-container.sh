#!/bin/bash

cd ./app
mvn clean package
docker cp ./target/*.war br.com.mobsolutions.eventos-app-c:/opt/jboss/wildfly/standalone/deployments/
cd ..
