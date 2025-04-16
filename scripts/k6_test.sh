#!/bin/bash

# 절대 경로로 프로젝트 루트 지정
PROJECT_ROOT="/Users/gimseogchan/dev/toy-project/multi/k6" # 여기를 본인 경로에 맞게 수정

cd "$PROJECT_ROOT"

echo "부하 테스트를 실행합니다...."
K6_OUT=influxdb=http://admin:admin1234@localhost:8086/k6 k6 run sample.js