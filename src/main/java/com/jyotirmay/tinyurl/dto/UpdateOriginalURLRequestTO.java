package com.jyotirmay.tinyurl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record UpdateOriginalURLRequestTO(

        @NotNull(message = "Original URL must not be null")
        @NotBlank(message = "Original URL must not be blank")
        @URL(message = "Original URL must be a valid URL")
        String originalURL,

        @NotNull(message = "Tiny URL must not be null")
        @NotBlank(message = "Tiny URL must not be blank")
        String tinyURL) {
}