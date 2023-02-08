package com.andresdkm.flux.service;

import com.andresdkm.flux.dto.Response;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class MathService implements IntConsumer {

    private IntConsumer sleepConsumer = new IntConsumer() {
        @Override
        public void accept(int i) {
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public Response findSquare(Integer input){
        return new Response(input * input);
    }

    public List<Response> multiplicationTable(Integer input){
        return IntStream.rangeClosed(1,10)
                .peek(i->SleepUtil.sleepSeconds(1))
                .peek(i-> System.out.println("processing " + i))
                .mapToObj(i -> new Response(i * input))
                .collect(Collectors.toList());
    }

    public void accept(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
