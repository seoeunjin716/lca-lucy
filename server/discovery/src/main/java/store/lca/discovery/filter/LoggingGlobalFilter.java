package store.lca.discovery.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 모든 요청/응답을 비동기로 로깅하는 글로벌 필터
 * WebFlux의 리액티브 스트림을 활용하여 논블로킹 로깅
 */
@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingGlobalFilter.class);

    /**
     * 리액티브 체인 방식으로 필터 처리
     * Mono를 반환하여 비동기 처리
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        
        // 요청 시작 시간 기록
        long startTime = System.currentTimeMillis();
        String requestId = request.getId();
        String path = request.getURI().getPath();
        String method = request.getMethod().toString();
        
        logger.info("🚀 [REQUEST] ID: {} | {} {} | Client: {}", 
            requestId, 
            method, 
            path,
            request.getRemoteAddress()
        );

        // chain.filter()를 호출하여 다음 필터로 진행
        // doOnSuccess와 doOnError를 사용하여 로깅 (비동기!)
        return chain.filter(exchange)
            .doOnSuccess(aVoid -> {
                ServerHttpResponse response = exchange.getResponse();
                long duration = System.currentTimeMillis() - startTime;
                
                logger.info("✅ [RESPONSE] ID: {} | {} {} | Status: {} | Duration: {}ms", 
                    requestId,
                    method,
                    path,
                    response.getStatusCode(),
                    duration
                );
            })
            .doOnError(error -> {
                long duration = System.currentTimeMillis() - startTime;
                logger.error("❌ [ERROR] ID: {} | {} {} | Duration: {}ms | Error: {}", 
                    requestId,
                    method,
                    path,
                    duration,
                    error.getMessage()
                );
            });
    }

    /**
     * 필터 우선순위 설정 (숫자가 낮을수록 먼저 실행)
     * -1로 설정하여 가장 먼저 실행되도록
     */
    @Override
    public int getOrder() {
        return -1;
    }
}

