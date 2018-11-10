#!/bin/bash

cp ../server/web-api/target/web-api-*.jar ./containers/inventory-web/contribution/web-api.jar

docker build -t inventory ./containers/inventory-web/
docker-compose  -f ./docker-compose.yml up -d $@