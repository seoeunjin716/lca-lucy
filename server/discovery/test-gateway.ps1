# WebFlux Gateway 테스트 스크립트
# Windows PowerShell에서 사용

Write-Host "🚀 WebFlux Gateway 테스트 시작" -ForegroundColor Cyan
Write-Host "================================" -ForegroundColor Cyan

$GATEWAY_URL = "http://localhost:8080"

# 1. Health Check
Write-Host ""
Write-Host "1. Health Check 테스트" -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "$GATEWAY_URL/actuator/health" -Method Get
    $response | ConvertTo-Json -Depth 3
    Write-Host "✅ Health Check 성공" -ForegroundColor Green
} catch {
    Write-Host "❌ Health Check 실패: $_" -ForegroundColor Red
}

# 2. Gateway Routes 확인
Write-Host ""
Write-Host "2. Gateway Routes 확인" -ForegroundColor Yellow
try {
    $routes = Invoke-RestMethod -Uri "$GATEWAY_URL/actuator/gateway/routes" -Method Get
    $routes | ForEach-Object {
        Write-Host "  - Route ID: $($_.route_id)" -ForegroundColor Cyan
        Write-Host "    URI: $($_.uri)" -ForegroundColor Gray
    }
    Write-Host "✅ Routes 조회 성공" -ForegroundColor Green
} catch {
    Write-Host "❌ Routes 조회 실패: $_" -ForegroundColor Red
}

# 3. 정상 요청 테스트
Write-Host ""
Write-Host "3. Soccer Service 정상 요청" -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "$GATEWAY_URL/soccer-service/api/teams" -Method Get
    if ($response.StatusCode -eq 200) {
        Write-Host "✅ 성공: HTTP $($response.StatusCode)" -ForegroundColor Green
    }
} catch {
    Write-Host "❌ 실패: $($_.Exception.Response.StatusCode.Value__)" -ForegroundColor Red
}

# 4. Rate Limiting 테스트
Write-Host ""
Write-Host "4. Rate Limiting 테스트 (30개 요청)" -ForegroundColor Yellow
$SUCCESS = 0
$RATE_LIMITED = 0

1..30 | ForEach-Object {
    try {
        $response = Invoke-WebRequest -Uri "$GATEWAY_URL/soccer-service/api/teams" -Method Get -ErrorAction Stop
        if ($response.StatusCode -eq 200) {
            $SUCCESS++
        }
    } catch {
        if ($_.Exception.Response.StatusCode.Value__ -eq 429) {
            $RATE_LIMITED++
        }
    }
    Write-Host "." -NoNewline
}

Write-Host ""
Write-Host "성공: $SUCCESS 개" -ForegroundColor Green
Write-Host "Rate Limited (429): $RATE_LIMITED 개" -ForegroundColor Red

# 5. Circuit Breaker 상태 확인
Write-Host ""
Write-Host "5. Circuit Breaker 상태 확인" -ForegroundColor Yellow
try {
    $cb = Invoke-RestMethod -Uri "$GATEWAY_URL/actuator/circuitbreakers" -Method Get
    $cb | ConvertTo-Json -Depth 3
    Write-Host "✅ Circuit Breaker 조회 성공" -ForegroundColor Green
} catch {
    Write-Host "❌ Circuit Breaker 조회 실패: $_" -ForegroundColor Red
}

# 6. Redis 키 확인
Write-Host ""
Write-Host "6. Redis Rate Limiter 키 확인" -ForegroundColor Yellow
try {
    $dockerExists = Get-Command docker -ErrorAction SilentlyContinue
    if ($dockerExists) {
        Write-Host "Redis에 저장된 Rate Limiter 키:"
        docker exec redis-server redis-cli -a lca1234 KEYS "request_rate_limiter*" 2>$null
    } else {
        Write-Host "Docker가 설치되어 있지 않습니다." -ForegroundColor Yellow
    }
} catch {
    Write-Host "Redis 키 조회 실패" -ForegroundColor Red
}

Write-Host ""
Write-Host "✅ 테스트 완료!" -ForegroundColor Green

