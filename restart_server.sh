#!/bin/bash

echo "🔧 Stopping spring-app1 and spring-app2..."
docker compose stop spring-app1 spring-app2

echo "🧹 Removing old containers..."
docker compose rm -f spring-app1 spring-app2

echo "spring file build...."
./gradlew build -x test

echo "🛠️ Rebuilding images..."
docker compose build spring-app1 spring-app2

echo "🚀 Restarting containers..."
docker compose up -d spring-app1 spring-app2

echo "nginx 서버를 재실행합니다...."
docker compose restart nginx

echo "✅ Done! spring-app1 and spring-app2 are running with the latest build."
