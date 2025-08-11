package com.jyotirmay.tinyurl.controller;

import com.jyotirmay.tinyurl.dto.CreateTinyURLRequestTO;
import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.dto.UpdateOriginalURLRequestTO;
import com.jyotirmay.tinyurl.exception.InvalidTinyURLException;
import com.jyotirmay.tinyurl.service.CreateTinyURLService;
import com.jyotirmay.tinyurl.service.DeleteTinyURLService;
import com.jyotirmay.tinyurl.service.GetOriginalURLService;
import com.jyotirmay.tinyurl.service.UpdateOriginalURLService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TinyURLController {

    private static final Logger LOGGER = LogManager.getLogger(TinyURLController.class);

    private CreateTinyURLService createTinyURLService;

    private UpdateOriginalURLService updateOriginalURLService;

    private GetOriginalURLService getOriginalURLService;

    private DeleteTinyURLService  deleteTinyURLService;

    public TinyURLController(CreateTinyURLService createTinyURLService,
                             UpdateOriginalURLService updateOriginalURLService,
                             GetOriginalURLService getOriginalURLService,
                             DeleteTinyURLService deleteTinyURLService) {
        this.createTinyURLService = createTinyURLService;
        this.updateOriginalURLService = updateOriginalURLService;
        this.getOriginalURLService = getOriginalURLService;
        this.deleteTinyURLService = deleteTinyURLService;
    }

    @PostMapping(value = "/tiny/url", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept-Version=v1")
    public ResponseEntity<TinyURLResponseTO> createTinyURL(@Valid @RequestBody CreateTinyURLRequestTO createTinyURLRequestTO) {
        TinyURLResponseTO tinyURLResponseTO = createTinyURLService.createTinyURL(createTinyURLRequestTO);
        LOGGER.info("Tiny URL created successfully for url: {} as {}", tinyURLResponseTO.originalURL(), tinyURLResponseTO.tinyURL());
        return new ResponseEntity<>(tinyURLResponseTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/tiny/url", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept-Version=v1")
    public ResponseEntity<TinyURLResponseTO> updateOriginalURL(@Valid @RequestBody UpdateOriginalURLRequestTO updateOriginalURLRequestTO) {
        TinyURLResponseTO tinyURLResponseTO = updateOriginalURLService.updateOriginalURL(updateOriginalURLRequestTO);
        LOGGER.info("Original URL updated successfully for tiny URL: {} with {}", tinyURLResponseTO.tinyURL(), tinyURLResponseTO.originalURL());
        return new ResponseEntity<>(tinyURLResponseTO, HttpStatus.OK);
    }

    @GetMapping(value = "/tiny/url/{tinyURL}", produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept-Version=v1")
    public ResponseEntity<TinyURLResponseTO> getOriginalURL(@PathVariable("tinyURL") String tinyURL) {
        if (tinyURL == null || tinyURL.isBlank()) {
            throw new InvalidTinyURLException("ERR400", "Invalid Tiny URL in the request");
        }
        TinyURLResponseTO tinyURLResponseTO = getOriginalURLService.getOriginalURL(tinyURL);
        LOGGER.info("Original URL fetched successfully for tiny URL: {} as {}", tinyURLResponseTO.tinyURL(), tinyURLResponseTO.originalURL());
        return new ResponseEntity<>(tinyURLResponseTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/tiny/url/{tinyURL}", produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept-Version=v1")
    public ResponseEntity<TinyURLResponseTO> deleteTinyURL(@PathVariable("tinyURL") String tinyURL) {
        if (tinyURL == null || tinyURL.isBlank()) {
            throw new InvalidTinyURLException("ERR400", "Invalid Tiny URL in the request");
        }
        TinyURLResponseTO tinyURLResponseTO = deleteTinyURLService.deleteTinyURL(tinyURL);
        LOGGER.info("Tiny URL deleted successfully for tiny URL: {}", tinyURLResponseTO.tinyURL());
        return new ResponseEntity<>(tinyURLResponseTO, HttpStatus.OK);
    }
}
