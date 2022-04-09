package com.example.library.config;

import com.example.library.controller.ResourceController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurerComposite;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {
    @Bean
    public RouterFunction<ServerResponse> routes(ResourceController resourceController) {
        return route(GET("/getAll"), resourceController::findAll)
                .andRoute(POST("/posts").and(contentType(APPLICATION_JSON)), resourceController::save)
                .andRoute(GET("/getByID/{id}"), resourceController::getById)
                .andRoute(PUT("/reserveByID/{id}"), resourceController::reserveByID)
                .andRoute(PUT("/returnByID/{id}"), resourceController::returnByID);
    }

    @Bean
    public WebFluxConfigurer corsConfigure() {
        return new WebFluxConfigurerComposite() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
}