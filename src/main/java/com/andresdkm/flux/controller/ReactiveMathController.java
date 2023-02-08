package com.andresdkm.flux.controller;

import com.andresdkm.flux.dto.Response;
import com.andresdkm.flux.exception.InputValidationException;
import com.andresdkm.flux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

    @Autowired
    private ReactiveMathService mathService;

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable Integer input) {
        return this.mathService.findSquare(input);
    }


    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquareThrow(@PathVariable Integer input) {
        if (input < 10 || input > 20) {
            throw new InputValidationException(input);
        }
        return this.mathService.findSquare(input);
    }

    @GetMapping("square/{input}/mono")
    public Mono<Response> findSquareMono(@PathVariable Integer input) {
        return Mono.just(input)
                .handle((number, sync) -> {
                    if (number >= 10 && number <= 20) {
                        sync.next(number);
                    } else {
                        sync.error(new InputValidationException(number));
                    }
                })
                .cast(Integer.class)
                .flatMap(i -> this.mathService.findSquare(i));
    }
}
