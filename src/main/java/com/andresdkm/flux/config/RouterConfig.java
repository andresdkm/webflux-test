package com.andresdkm.flux.config;

import com.andresdkm.flux.dto.InputFailedValidationResponse;
import com.andresdkm.flux.exception.InputValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;


/**
 * this allows the creation of functional routing
 */
@Configuration
public class RouterConfig {

    @Autowired
    RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> masterRoutes(){
        return RouterFunctions.route()
                .path("router", this::serverResponseRouterFunction)
                .onError(InputValidationException.class, exceptionHandler())
                .build();
    }

    public RouterFunction<ServerResponse> serverResponseRouterFunction(){
        return RouterFunctions.route()
                .GET("/square/{input}", requestHandler::squareHandler)
                .GET("/square/{input}/validation", requestHandler::squareHandlerWithValidation)
                .onError(InputValidationException.class, exceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler(){
        return (err, req) -> {
            InputValidationException exception = (InputValidationException)err;
            InputFailedValidationResponse response = new InputFailedValidationResponse();
            response.setInput(exception.getInput());
            response.setMessage(exception.getMessage());
            response.setErrorCode(InputValidationException.errorCode);
            return ServerResponse.badRequest().bodyValue(response);
        };
    }
}
