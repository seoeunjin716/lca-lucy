package store.lca.discovery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 서킷 브레이커가 작동했을 때 호출되는 Fallback 컨트롤러
 * 백엔드 서비스가 다운되었을 때 사용자에게 친절한 메시지를 반환
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {

    /**
     * User Service Fallback
     * 리액티브 타입인 Mono를 반환 → 논블로킹!
     */
    @GetMapping("/user-service")
    public Mono<ResponseEntity<Map<String, Object>>> userServiceFallback() {
        return Mono.just(
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(createFallbackResponse("User Service"))
        );
    }

    /**
     * Soccer Service Fallback
     */
    @GetMapping("/soccer-service")
    public Mono<ResponseEntity<Map<String, Object>>> soccerServiceFallback() {
        return Mono.just(
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(createFallbackResponse("Soccer Service"))
        );
    }

    /**
     * 공통 Fallback 응답 생성
     */
    private Map<String, Object> createFallbackResponse(String serviceName) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Service Temporarily Unavailable");
        response.put("message", serviceName + "가 일시적으로 사용할 수 없습니다. 잠시 후 다시 시도해주세요.");
        response.put("service", serviceName);
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.SERVICE_UNAVAILABLE.value());
        return response;
    }
}

