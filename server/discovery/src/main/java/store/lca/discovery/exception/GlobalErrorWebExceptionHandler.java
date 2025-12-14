package store.lca.discovery.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 글로벌 에러 핸들러 (리액티브)
 * 모든 예외를 비동기로 처리하고 JSON 응답 반환
 */
@Component
@Order(-2)  // 기본 에러 핸들러보다 먼저 실행
public class GlobalErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorWebExceptionHandler.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 예외 처리 (리액티브)
     * Mono를 반환하여 비동기 처리
     */
    @Override
    @NonNull
    @SuppressWarnings("null")
    public Mono<Void> handle(@NonNull ServerWebExchange exchange, @NonNull Throwable ex) {
        logger.error("❌ Global Error Handler - {}: {}", 
            ex.getClass().getSimpleName(), 
            ex.getMessage()
        );

        HttpStatus status = determineHttpStatus(ex);
        
        // 응답 설정
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // 에러 응답 생성
        Map<String, Object> errorResponse = createErrorResponse(ex, status);

        // JSON으로 변환 및 응답
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(errorResponse);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            byte[] errorBytes = "Internal Server Error".getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(errorBytes);
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }
    }

    /**
     * HTTP 상태 코드 결정
     */
    private HttpStatus determineHttpStatus(Throwable ex) {
        if (ex instanceof ResponseStatusException) {
            return HttpStatus.valueOf(((ResponseStatusException) ex).getStatusCode().value());
        }
        
        // 기본적으로 500 에러
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * 에러 응답 생성
     */
    private Map<String, Object> createErrorResponse(Throwable ex, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", ex.getMessage() != null ? ex.getMessage() : "예상치 못한 오류가 발생했습니다.");
        response.put("exception", ex.getClass().getSimpleName());
        
        return response;
    }
}

