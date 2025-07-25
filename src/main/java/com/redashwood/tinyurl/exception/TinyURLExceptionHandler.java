/*
 * Copyright (c) 2025 Jyotirmay Gupta
 *
 * Project: Tiny URL
 * Description: This is a personal project by Jyotirmay Gupta that implements a
 * simplified URL shortening service, similar in concept to services like bit.ly or tinyurl.com.
 * It allows long URLs to be converted into compact, easy-to-share short URLs.
 *
 * This code is intended for educational and personal use, demonstrating core backend
 * concepts such as encoding algorithms, database storage, and URL mapping.
 *
 * Licensed under the Apache License Version 2.0. See LICENSE file for more details.
 */
package com.redashwood.tinyurl.exception;

import com.redashwood.tinyurl.dto.ErrorTO;
import com.redashwood.tinyurl.dto.TinyURLResponseTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TinyURLExceptionHandler {

    @ExceptionHandler(InvalidTinyURLException.class)
    public ResponseEntity<TinyURLResponseTO> handleInvalidTinyURLException(InvalidTinyURLException ex) {

        ErrorTO errorTO = new ErrorTO(ex.getErrorCode(), ex.getMessage());
        TinyURLResponseTO tinyURLResponseTO = new TinyURLResponseTO(null, null, null, null, errorTO);

        return new ResponseEntity<>(tinyURLResponseTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TinyURLNotFoundException.class)
    public ResponseEntity<TinyURLResponseTO> handleTinyURLNotFoundException(TinyURLNotFoundException ex) {

        ErrorTO errorTO = new ErrorTO(ex.getErrorCode(), ex.getMessage());
        TinyURLResponseTO tinyURLResponseTO = new TinyURLResponseTO(null, null, null, null, errorTO);

        return new ResponseEntity<>(tinyURLResponseTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<TinyURLResponseTO> handleValidationErrors(MethodArgumentNotValidException ex) {

        FieldError fieldError = ex.getBindingResult().getFieldErrors().getFirst();
        ErrorTO errorTO = new ErrorTO("ERR400", fieldError.getDefaultMessage());


        TinyURLResponseTO tinyURLResponseTO = new TinyURLResponseTO(null, null, null, null, errorTO);

        return new ResponseEntity<>(tinyURLResponseTO, HttpStatus.BAD_REQUEST);
    }
}
