#!/bin/bash

# Gradle 빌드 (테스트 제외)
./gradlew build -x test

# Docker Compose로 컨테이너 백그라운드 실행
docker compose up -d --build
