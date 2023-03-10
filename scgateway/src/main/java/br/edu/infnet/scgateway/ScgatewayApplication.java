package br.edu.infnet.scgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ScgatewayApplication {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/clientes/**")
                        .filters(f -> f.circuitBreaker(
                                config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback-cliente")
                        ))
                        .uri("http://localhost:8282")
                )
                .route(p -> p
                        .path("/equipamentos/**")
                        .filters(f -> f.circuitBreaker(
                                config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback-catalogo")
                        ))
                        .uri("http://localhost:8383")
                )
                .route(p -> p
                        .path("/locacoes/**")
                        .filters(f -> f.circuitBreaker(
                                config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback-locacao")
                                ))
                        .uri("http://localhost:8181")
                )
                .build();
    }

    @GetMapping("/fallback-catalogo")
    public String fallbackCatalogo() {
        return "Servi??o de catalogo indispon??vel, volte mais tarde!!!";
    }

    @GetMapping("/fallback-cliente")
    public String fallbackCliente() {
        return "Servi??o de catalogo indispon??vel, volte mais tarde!!!";
    }

    @GetMapping("/fallback-locacao")
    public String fallbackLocacao() {
        return "Servi??o de loca????o indispon??vel, volte mais tarde!";
    }

    public static void main(String[] args) {
        SpringApplication.run(ScgatewayApplication.class, args);
    }

}
