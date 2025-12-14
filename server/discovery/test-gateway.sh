#!/bin/bash

# WebFlux Gateway 테스트 스크립트
# Linux/Mac/Git Bash에서 사용

echo "🚀 WebFlux Gateway 테스트 시작"
echo "================================"

GATEWAY_URL="http://localhost:8080"

# 색상 코드
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 1. Health Check
echo ""
echo "${YELLOW}1. Health Check 테스트${NC}"
echo "curl $GATEWAY_URL/actuator/health"
curl -s $GATEWAY_URL/actuator/health | jq .
echo ""

# 2. Gateway Routes 확인
echo "${YELLOW}2. Gateway Routes 확인${NC}"
echo "curl $GATEWAY_URL/actuator/gateway/routes"
curl -s $GATEWAY_URL/actuator/gateway/routes | jq '.[] | {id: .route_id, uri: .uri, predicates: .predicates}'
echo ""

# 3. 정상 요청 테스트
echo "${YELLOW}3. Soccer Service 정상 요청${NC}"
echo "curl $GATEWAY_URL/soccer-service/api/teams"
HTTP_CODE=$(curl -s -o /dev/null -w "%{http_code}" $GATEWAY_URL/soccer-service/api/teams)
if [ $HTTP_CODE -eq 200 ]; then
    echo "${GREEN}✅ 성공: HTTP $HTTP_CODE${NC}"
else
    echo "${RED}❌ 실패: HTTP $HTTP_CODE${NC}"
fi
echo ""

# 4. Rate Limiting 테스트
echo "${YELLOW}4. Rate Limiting 테스트 (30개 요청)${NC}"
SUCCESS=0
RATE_LIMITED=0

for i in {1..30}; do
    HTTP_CODE=$(curl -s -o /dev/null -w "%{http_code}" $GATEWAY_URL/soccer-service/api/teams)
    if [ $HTTP_CODE -eq 200 ]; then
        ((SUCCESS++))
    elif [ $HTTP_CODE -eq 429 ]; then
        ((RATE_LIMITED++))
    fi
    echo -n "."
done
echo ""
echo "${GREEN}성공: $SUCCESS 개${NC}"
echo "${RED}Rate Limited (429): $RATE_LIMITED 개${NC}"
echo ""

# 5. Circuit Breaker 상태 확인
echo "${YELLOW}5. Circuit Breaker 상태 확인${NC}"
curl -s $GATEWAY_URL/actuator/circuitbreakers | jq .
echo ""

# 6. Redis 키 확인 (Redis CLI 필요)
echo "${YELLOW}6. Redis Rate Limiter 키 확인${NC}"
if command -v docker &> /dev/null; then
    echo "Redis에 저장된 Rate Limiter 키:"
    docker exec redis-server redis-cli -a lca1234 KEYS "request_rate_limiter*" 2>/dev/null || echo "Redis에 접근할 수 없습니다."
else
    echo "Docker가 설치되어 있지 않습니다."
fi
echo ""

echo "${GREEN}✅ 테스트 완료!${NC}"

