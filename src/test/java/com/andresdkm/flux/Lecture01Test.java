package com.andresdkm.flux;

import com.andresdkm.flux.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.IntStream;

public class Lecture01Test extends FluxApplicationTests {

    @Autowired
    private WebClient webClient;

    @Test
    public void blockTest() {

        IntStream.range(0, 100)
                .forEach(n -> {
                    Response response = this.webClient
                            .get()
                            .uri("reactive-math/square/{number}", n)
                            .retrieve()
                            .bodyToMono(Response.class)
                            .block();
                    System.out.println(response);
                });


    }


    @Test
    public void noBlockTest() {

        IntStream.range(0, 100)
                .forEach(n -> {
                    Mono<Response> response = this.webClient
                            .get()
                            .uri("reactive-math/square/{number}", n)
                            .retrieve()
                            .bodyToMono(Response.class);
                    StepVerifier.create(response)
                            .expectNextMatches(r -> r.getNumber() == n*n+1)
                            .verifyComplete();
                });


    }

}
