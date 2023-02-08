package com.andresdkm.flux;

import com.andresdkm.flux.dto.InputFailedValidationResponse;
import com.andresdkm.flux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture04Test extends FluxApplicationTests{

    @Autowired
    private WebClient webClient;

    @Test
    public void badRequestTest(){
        Mono<Object> responseMono = this.webClient
                .get()
                .uri("reactive-math/square/{number}/throw", 5)
                .exchangeToMono(this::exchange)
                .doOnNext(System.out::println)
                .doOnError(err -> System.out.println(err.getMessage()));

        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private Mono<Object> exchange(ClientResponse cr){
        if(cr.rawStatusCode() == 400){
            return cr.bodyToMono(InputFailedValidationResponse.class);
        }else{
            return cr.bodyToMono(Response.class);
        }
    }
}
