package com.jyotirmay.tinyurl.controller;

import com.jyotirmay.tinyurl.dto.CreateTinyURLRequestTO;
import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.service.TinyURLService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TinyURLController {

    private static final Logger LOGGER = LogManager.getLogger(TinyURLController.class);

    private TinyURLService tinyURLService;

    public TinyURLController(TinyURLService tinyURLService) {
        this.tinyURLService = tinyURLService;
    }

    @PostMapping(value = "/tiny/url", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept-Version=v1")
    public ResponseEntity<TinyURLResponseTO> tinyURL(@RequestBody CreateTinyURLRequestTO createTinyURLRequestTO) {
        return new ResponseEntity<>(tinyURLService.createTinyURL(createTinyURLRequestTO), HttpStatus.OK);
    }
}
