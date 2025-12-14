# WebFlux 논블로킹 게이트웨이 전략 가이드 🚀

## 📚 초보자를 위한 개념 설명

### 1. 블로킹 vs 논블로킹

#### 🐌 블로킹 (기존 방식)
```
요청 → [쓰레드 대기] → 응답 → 다음 요청
- 1개 요청 = 1개 쓰레드
- 100명 접속 = 100개 쓰레드 필요
- 메모리 많이 사용
```

#### ⚡ 논블로킹 (WebFlux)
```
요청1 → [처리 중...] 
요청2 → [처리 중...] → 같은 쓰레드로 여러 요청 처리!
요청3 → [처리 중...]
- 1개 쓰레드로 수천 개 요청 처리
- 메모리 효율적
```

### 2. 리액티브 프로그래밍이란?

**일반적인 방식:**
```java
String result = service.getData();  // 데이터 올 때까지 기다림 (블로킹)
return result;
```

**리액티브 방식:**
```java
Mono<String> result = service.getData();  // 즉시 반환 (논블로킹)
return result;  // 나중에 데이터가 오면 자동으로 처리
```

`Mono`는 "나중에 1개의 데이터가 올 것"이라는 약속!

---

## 🎯 구현된 기능

### ✅ 1단계: Redis 리액티브 통합
- **목적**: 비동기 캐싱 및 Rate Limiting
- **효과**: DB 부하 감소, 빠른 응답

### ✅ 2단계: Circuit Breaker (서킷 브레이커)
- **목적**: 백엔드 서비스 장애 시 자동 차단
- **효과**: 장애 전파 방지, 안정적인 서비스

**작동 방식:**
```
1. 정상: 모든 요청 통과
2. 오류 50% 초과: 서킷 오픈 (요청 차단)
3. 10초 후: Half-Open (테스트)
4. 성공 시: 다시 정상으로
```

### ✅ 3단계: Rate Limiting (요청 제한)
- **목적**: DDoS 공격 방어, 서버 보호
- **효과**: IP당 초당 요청 수 제한

**현재 설정:**
- User Service: 초당 10개 요청, 버스트 20개
- Soccer Service: 초당 20개 요청, 버스트 40개

### ✅ 4단계: 로깅 필터
- **목적**: 모든 요청/응답 로깅
- **효과**: 디버깅 용이, 성능 모니터링

### ✅ 5단계: 글로벌 에러 핸들러
- **목적**: 통일된 에러 응답
- **효과**: 사용자 친화적 에러 메시지

---

## 📁 파일 구조

```
server/discovery/
├── src/main/java/store/lca/discovery/
│   ├── DiscoveryApplication.java        # 메인 클래스
│   ├── controller/
│   │   └── FallbackController.java      # Fallback 처리
│   ├── filter/
│   │   └── LoggingGlobalFilter.java     # 로깅 필터
│   ├── config/
│   │   └── RateLimiterConfig.java       # Rate Limiter 설정
│   └── exception/
│       └── GlobalErrorWebExceptionHandler.java  # 에러 핸들러
├── src/main/resources/
│   └── application.yml                  # 설정 파일
├── test-gateway.sh                      # Linux/Mac 테스트 스크립트
└── test-gateway.ps1                     # Windows 테스트 스크립트
```

> **참고**: Redis 설정은 Spring Cloud Gateway가 자동으로 처리하므로 별도 Config 클래스가 필요 없습니다.

---

## 🚀 사용 방법

### 1. 빌드 및 실행

```bash
# 프로젝트 루트에서
docker-compose up --build

# 또는 개별 실행
cd server/discovery
./gradlew bootRun
```

### 2. 테스트

#### 정상 요청
```bash
curl http://localhost:8080/soccer-service/api/teams
```

#### Rate Limiting 테스트 (초당 20개 이상 요청)
```bash
# Linux/Mac
for i in {1..30}; do curl http://localhost:8080/soccer-service/api/teams; done

# PowerShell (Windows)
1..30 | ForEach-Object { 
    Invoke-WebRequest http://localhost:8080/soccer-service/api/teams 
}
```

**기대 결과:** 일부 요청은 `429 Too Many Requests` 응답

#### Circuit Breaker 테스트
```bash
# Soccer Service 중지
docker stop soccer-service

# 요청 보내기
curl http://localhost:8080/soccer-service/api/teams

# Fallback 응답 확인
```

**기대 결과:**
```json
{
  "error": "Service Temporarily Unavailable",
  "message": "Soccer Service가 일시적으로 사용할 수 없습니다...",
  "status": 503
}
```

### 3. 모니터링

#### Actuator 엔드포인트
```bash
# 전체 상태 확인
curl http://localhost:8080/actuator/health

# Gateway 라우트 확인
curl http://localhost:8080/actuator/gateway/routes

# Circuit Breaker 상태
curl http://localhost:8080/actuator/circuitbreakers
```

#### Redis 상태 확인
```bash
# Redis CLI 접속
docker exec -it redis-server redis-cli -a lca1234

# Rate Limiter 키 확인
KEYS request_rate_limiter*

# 특정 IP의 요청 수 확인
GET request_rate_limiter.<IP주소>
```

---

## 🔧 설정 커스터마이징

### Rate Limit 조정

`application.yml` 파일에서:

```yaml
filters:
  - name: RequestRateLimiter
    args:
      redis-rate-limiter.replenishRate: 10    # 초당 요청 수
      redis-rate-limiter.burstCapacity: 20     # 최대 버스트
```

### Circuit Breaker 조정

```yaml
resilience4j:
  circuitbreaker:
    configs:
      default:
        failureRateThreshold: 50      # 실패율 (50%)
        waitDurationInOpenState: 10s  # OPEN 상태 유지 시간
```

### Timeout 조정

```yaml
spring:
  cloud:
    gateway:
      httpclient:
        connect-timeout: 3000    # 연결 타임아웃 (ms)
        response-timeout: 10s    # 응답 타임아웃
```

---

## 📊 성능 비교

| 항목 | 블로킹 (MVC) | 논블로킹 (WebFlux) |
|------|-------------|-------------------|
| 동시 처리 | 100명 | 10,000명+ |
| 메모리 사용 | 높음 | 낮음 |
| 쓰레드 수 | 많음 | 적음 |
| 응답 속도 | 보통 | 빠름 |
| 복잡도 | 낮음 | 중간 |

---

## 🎓 학습 순서 (초보자용)

1. **Mono와 Flux 이해**
   - Mono: 0-1개 데이터
   - Flux: 0-N개 데이터

2. **LoggingGlobalFilter 코드 읽기**
   - 가장 간단한 필터 예제

3. **FallbackController 코드 읽기**
   - Mono를 반환하는 방법 학습

4. **RateLimiterConfig 코드 읽기**
   - KeyResolver 패턴 이해

5. **application.yml 설정 실험**
   - 값 변경 → 재시작 → 테스트

---

## 🐛 트러블슈팅

### 1. Redis 연결 실패
```bash
# Redis 상태 확인
docker ps | grep redis

# 로그 확인
docker logs redis-server

# 재시작
docker-compose restart redis
```

### 2. Circuit Breaker 작동 안 함
- `resilience4j` 의존성 확인
- `minimumNumberOfCalls` 값 확인 (5회 이상 호출 필요)
- 로그에서 실패율 확인

### 3. Rate Limiting 작동 안 함
- `ipKeyResolver` Bean이 등록되었는지 확인
- Redis에 키가 생성되는지 확인
- `application.yml`에서 KeyResolver 명시:
  ```yaml
  filters:
    - name: RequestRateLimiter
      args:
        key-resolver: "#{@ipKeyResolver}"
  ```

---

## 📖 추가 학습 자료

- [Spring Cloud Gateway 공식 문서](https://spring.io/projects/spring-cloud-gateway)
- [Project Reactor 가이드](https://projectreactor.io/docs)
- [Resilience4j 문서](https://resilience4j.readme.io/)

---

## 💡 다음 단계

1. **JWT 인증 추가**: 사용자 인증 필터 구현
2. **분산 트레이싱**: Zipkin/Sleuth 통합
3. **메트릭 수집**: Prometheus + Grafana
4. **API 버전 관리**: 버전별 라우팅
5. **WebSocket 지원**: 실시간 통신 게이트웨이

---

## ✨ 핵심 정리

### WebFlux를 사용하는 이유
1. **높은 동시성**: 적은 리소스로 많은 요청 처리
2. **빠른 응답**: 논블로킹으로 지연 시간 감소
3. **확장성**: 수평 확장 용이

### 주의사항
- **블로킹 코드 금지**: JDBC, Thread.sleep() 등 사용 불가
- **리액티브 체인 유지**: Mono/Flux 체인 끊지 말기
- **백프레셔 관리**: 데이터 과부하 방지

### 언제 사용하면 좋을까?
- ✅ API Gateway (현재 구조)
- ✅ 실시간 스트리밍
- ✅ 채팅 서버
- ✅ IoT 데이터 수집
- ❌ 간단한 CRUD (오버엔지니어링)
- ❌ 레거시 DB만 사용 (블로킹)

---

**작성일**: 2025-11-24
**버전**: 1.0
**유지보수**: Spring Boot 3.x, Java 21 기준

