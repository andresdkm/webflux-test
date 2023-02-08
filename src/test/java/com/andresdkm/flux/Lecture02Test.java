package com.andresdkm.flux;

import com.andresdkm.flux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.IntStream;

public class Lecture02Test extends FluxApplicationTests {

    @Autowired
    private WebClient webClient;

    @Test
    public void fluxTest() {

        Flux<Response> response = this.webClient
                .get()
                .uri("math/flux/multiplication/{input}", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(response)
                .expectNextCount(10)
                .verifyComplete();


    }


    @Test
    public void fluxTestStream() {

        Flux<Response> response = this.webClient
                .get()
                .uri("math/flux/multiplication/{input}/stream", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);

        StepVerifier.create(response)
                .expectNextCount(10)
                .verifyComplete();


    }

}
