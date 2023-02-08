package com.andresdkm.flux.controller;

import com.andresdkm.flux.dto.Response;
import com.andresdkm.flux.exception.InputValidationException;
import com.andresdkm.flux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-test")
public class TestController {

    @Autowired
    private ReactiveMathService mathService;

    @GetMapping("square/{input}/mono")
    public Mono<ResponseEntity<Response>> findSquareMono(@PathVariable Integer input){
        return Mono.just(input)
                .filter(i -> i >= 10 && i <= 20)
                .flatMap(i -> mathService.findSquare(i))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
