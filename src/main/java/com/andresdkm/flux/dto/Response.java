package com.andresdkm.flux.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor // created a default construct
public class Response {

    private Date date = new Date();
    private Integer number;

    public Response(Integer number) {
        this.number = number;
    }
}
