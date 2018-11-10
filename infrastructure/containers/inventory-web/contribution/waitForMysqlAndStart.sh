#!/bin/sh
i=0
while ! nc "mysql" "3306" >/dev/null 2>&1 < /dev/null; do
  i=`expr $i + 1`
  if [ $i -ge 50 ]; then
    echo "$(date) - mysql:3306 still not reachable, giving up"
    exit 1
  fi
  echo "$(date) - waiting for mysql:3306..."
  sleep 1
done
echo "mysql connection established"

/usr/bin/java -jar /mnt/contribution/web-api.jar