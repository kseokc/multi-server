#!/bin/bash

# 절대 경로로 프로젝트 루트 지정
PROJECT_ROOT="/Users/gimseogchan/dev/toy-project/multi" # 여기를 본인 경로에 맞게 수정

cd "$PROJECT_ROOT"

# Gradle 빌드 (테스트 제외)
./gradlew build -x test

# Docker Compose로 컨테이너 백그라운드 실행
docker compose up -d --build
