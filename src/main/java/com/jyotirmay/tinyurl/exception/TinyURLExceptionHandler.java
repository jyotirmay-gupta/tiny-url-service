package com.jyotirmay.tinyurl.exception;

import com.jyotirmay.tinyurl.dto.ErrorTO;
import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
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
