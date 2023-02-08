package com.andresdkm.flux.config;

import com.andresdkm.flux.dto.Response;
import com.andresdkm.flux.exception.InputValidationException;
import com.andresdkm.flux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {

    @Autowired
    private ReactiveMathService mathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Mono<Response> responseMono = this.mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if(input <=10 || input >=20)
            return Mono.error(new InputValidationException(input));
        return ServerResponse.ok().body( this.mathService.findSquare(input), Response.class);

    }
}
