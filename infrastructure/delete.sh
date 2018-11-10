#!/bin/bash

echo stopping containers:
docker stop inventory-web
docker stop inventory-mysql

echo removing containers:
docker rm inventory-web
docker rm inventory-mysql