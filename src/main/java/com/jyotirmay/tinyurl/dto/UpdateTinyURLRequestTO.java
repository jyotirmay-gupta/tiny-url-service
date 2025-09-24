package com.jyotirmay.tinyurl.dto;

public record UpdateTinyURLRequestTO(

        String originalUrl,

        String tinyUrl
) {
}
