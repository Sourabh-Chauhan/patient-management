package com.pm.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private final WebClient webClient;

    @Autowired
    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder, @Value("${auth-service-route}") String authServiceUrl) {
//        super(Object.class);
        System.out.println(authServiceUrl);
        this.webClient = webClientBuilder.baseUrl(authServiceUrl).build();
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
//            if (!Boolean.parseBoolean(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))) {
//                return onError(exchange, "No Authorization Header", HttpStatus.UNAUTHORIZED);
//            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Invalid Authorization Header", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);
            System.out.println("TOKEN : "+token);

            return webClient.get()
                    .uri("/validate")
                    .header(HttpHeaders.AUTHORIZATION, authHeader)
                    .retrieve()
//                    .bodyToMono(Void.class)
                    .toBodilessEntity()
                    .then(chain.filter(exchange))
                    .onErrorResume(ex -> onError(exchange, "Invalid Token", HttpStatus.UNAUTHORIZED));
//            System.out.println(webClient);
//            return webClient.get()
//                    .uri("/validate")
//                    .header(HttpHeaders.AUTHORIZATION, authHeader)
//                    .retrieve()
//                    .toBodilessEntity()
//                    .then(chain.filter(exchange));
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        exchange.getResponse().setStatusCode(httpStatus);
        System.out.println("error "+ err);
        return exchange.getResponse().setComplete();
    }
}
