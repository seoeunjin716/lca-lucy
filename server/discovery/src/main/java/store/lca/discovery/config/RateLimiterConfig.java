package store.lca.discovery.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Rate Limiter 설정
 * Redis를 사용하여 요청 제한 (논블로킹 방식)
 */
@Configuration
public class RateLimiterConfig {

    /**
     * IP 주소 기반 Rate Limiting
     * 같은 IP에서 오는 요청을 제한
     * 
     * Mono를 반환하여 비동기 처리!
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> {
            // 클라이언트 IP 주소를 키로 사용
            String clientIp = Objects.requireNonNull(
                exchange.getRequest().getRemoteAddress()
            ).getAddress().getHostAddress();
            
            // Mono로 감싸서 반환 (리액티브!)
            return Mono.just(clientIp);
        };
    }

    /**
     * 사용자 ID 기반 Rate Limiting (선택사항)
     * 인증이 구현되면 사용자별로 제한 가능
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> {
            // 헤더에서 사용자 ID 추출 (예시)
            String userId = exchange.getRequest()
                .getHeaders()
                .getFirst("X-User-Id");
            
            // 사용자 ID가 없으면 IP 주소 사용
            if (userId == null || userId.isEmpty()) {
                String clientIp = Objects.requireNonNull(
                    exchange.getRequest().getRemoteAddress()
                ).getAddress().getHostAddress();
                return Mono.just(clientIp);
            }
            
            return Mono.just(userId);
        };
    }

    /**
     * API Path 기반 Rate Limiting (선택사항)
     * 특정 API 경로별로 제한
     */
    @Bean
    public KeyResolver pathKeyResolver() {
        return exchange -> {
            String path = exchange.getRequest().getURI().getPath();
            return Mono.just(path);
        };
    }
}

