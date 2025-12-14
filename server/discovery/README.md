# Discovery Server (WebFlux API Gateway) 🚀

> Spring Cloud Gateway 기반 논블로킹 API Gateway

## 🎯 주요 기능

- ✅ **WebFlux 기반 논블로킹 아키텍처**
- ✅ **Redis Rate Limiting** (DDoS 방어)
- ✅ **Circuit Breaker** (장애 전파 방지)
- ✅ **리액티브 로깅** (요청/응답 추적)
- ✅ **글로벌 에러 핸들링**
- ✅ **Eureka 서비스 디스커버리 통합**

## 🚀 빠른 시작

### Docker Compose로 실행
```bash
# 프로젝트 루트에서
docker-compose up -d eureka-server redis discovery-server
```

### 로컬 개발 환경
```bash
cd server/discovery
./gradlew bootRun
```

## 📝 테스트

### Windows (PowerShell)
```powershell
.\test-gateway.ps1
```

### Linux/Mac
```bash
chmod +x test-gateway.sh
./test-gateway.sh
```

### 수동 테스트
```bash
# Health Check
curl http://localhost:8080/actuator/health

# Soccer Service 호출
curl http://localhost:8080/soccer-service/api/teams

# Rate Limiting 테스트 (PowerShell)
1..30 | ForEach-Object { Invoke-WebRequest http://localhost:8080/soccer/api/teams }
```

## 📚 상세 가이드

자세한 내용은 [WEBFLUX_STRATEGY.md](./WEBFLUX_STRATEGY.md)를 참고하세요.

## 🔧 환경 변수

| 변수 | 기본값 | 설명 |
|------|--------|------|
| `SPRING_REDIS_HOST` | localhost | Redis 호스트 |
| `SPRING_REDIS_PORT` | 6379 | Redis 포트 |
| `SPRING_REDIS_PASSWORD` | - | Redis 비밀번호 |
| `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE` | http://localhost:8761/eureka/ | Eureka 서버 URL |

## 📊 모니터링 엔드포인트

- `/actuator/health` - 서비스 상태
- `/actuator/gateway/routes` - 라우트 정보
- `/actuator/circuitbreakers` - Circuit Breaker 상태
- `/actuator/metrics` - 메트릭

## 🏗️ 아키텍처

```
Client → Discovery Gateway (WebFlux) → Backend Services
              ↓
            Redis (Rate Limiting + Cache)
              ↓
          Eureka (Service Discovery)
```

## 📖 학습 자료

- [WebFlux 전략 가이드](./WEBFLUX_STRATEGY.md) - 초보자용 상세 설명
- [Spring Cloud Gateway 공식 문서](https://spring.io/projects/spring-cloud-gateway)
- [Project Reactor](https://projectreactor.io/)

## 🐛 문제 해결

### Redis 연결 오류
```bash
docker logs redis-server
docker-compose restart redis
```

### Circuit Breaker 작동 확인
```bash
curl http://localhost:8080/actuator/circuitbreakers | jq .
```

### Rate Limiting 키 확인
```bash
docker exec redis-server redis-cli -a lca1234 KEYS "*"
```

## 📝 라이센스

MIT License

