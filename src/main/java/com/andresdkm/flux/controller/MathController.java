package com.andresdkm.flux.controller;

import com.andresdkm.flux.dto.Response;
import com.andresdkm.flux.service.MathService;
import com.andresdkm.flux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("math")
public class MathController {

    @Autowired
    private MathService mathService;

    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping("square/{input}")
    public Response findSquare(@PathVariable Integer input){
        return this.mathService.findSquare(input);
    }

    @GetMapping("multiplication/{input}")
    public List<Response> multiplication(@PathVariable Integer input){
        return this.mathService.multiplicationTable(input);
    }

    @GetMapping("flux/square/{input}")
    public Mono<Response> findSquareFlux(@PathVariable Integer input){
        return this.reactiveMathService.findSquare(input);
    }

    @GetMapping(value = "flux/multiplication/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationFlux(@PathVariable Integer input){
        return this.reactiveMathService.multiplicationTable(input);
    }

    @GetMapping(value = "flux/multiplication/{input}")
    public Flux<Response> multiplicationFluxStream(@PathVariable Integer input){
        return this.reactiveMathService.multiplicationTable(input);
    }
}
