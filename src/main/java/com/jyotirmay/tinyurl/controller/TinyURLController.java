package com.jyotirmay.tinyurl.controller;

import com.jyotirmay.tinyurl.dto.CreateTinyURLRequestTO;
import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.dto.UpdateTinyURLRequestTO;
import com.jyotirmay.tinyurl.service.TinyURLService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TinyURLController {

    private static final Logger LOGGER = LogManager.getLogger(TinyURLController.class);

    private TinyURLService tinyURLService;

    public TinyURLController(TinyURLService tinyURLService) {
        this.tinyURLService = tinyURLService;
    }

    @PostMapping(value = "/tiny/url", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept-Version=v1")
    public ResponseEntity<TinyURLResponseTO> createTinyURL(@RequestBody CreateTinyURLRequestTO createTinyURLRequestTO) {
        return new ResponseEntity<>(tinyURLService.createTinyURL(createTinyURLRequestTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/tiny/url", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept-Version=v1")
    public ResponseEntity<TinyURLResponseTO> updateTinyURL(@RequestBody UpdateTinyURLRequestTO updateTinyURLRequestTO) {
        return new ResponseEntity<>(tinyURLService.updateTinyURL(updateTinyURLRequestTO), HttpStatus.OK);
    }

    @GetMapping(value = "/{tinyURL}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TinyURLResponseTO> getTinyURL(@PathVariable("tinyURL") String tinyURL) {
        return new ResponseEntity<>(tinyURLService.getOriginalURL(tinyURL), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{tinyURL}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TinyURLResponseTO> deleteTinyURL(@PathVariable("tinyURL") String tinyURL) {
        return new ResponseEntity<>(tinyURLService.deleteTinyURL(tinyURL), HttpStatus.OK);
    }
}
