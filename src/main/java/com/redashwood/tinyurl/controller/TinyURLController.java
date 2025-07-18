package com.redashwood.tinyurl.controller;

import com.redashwood.tinyurl.dto.CreateTinyURLRequestTO;
import com.redashwood.tinyurl.dto.TinyURLResponseTO;
import com.redashwood.tinyurl.dto.UpdateOriginalURLRequestTO;
import com.redashwood.tinyurl.exception.InvalidTinyURLException;
import com.redashwood.tinyurl.logger.LogExecutionTime;
import com.redashwood.tinyurl.service.CreateTinyURLService;
import com.redashwood.tinyurl.service.DeleteTinyURLService;
import com.redashwood.tinyurl.service.GetOriginalURLService;
import com.redashwood.tinyurl.service.UpdateOriginalURLService;
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

    private final CreateTinyURLService createTinyURLService;

    private final UpdateOriginalURLService updateTinyURLService;

    private final GetOriginalURLService getOriginalURLService;

    private final DeleteTinyURLService deleteTinyURLService;

    public TinyURLController(CreateTinyURLService createTinyURLService, UpdateOriginalURLService updateTinyURLService,
                             GetOriginalURLService getOriginalURLService, DeleteTinyURLService deleteTinyURLService) {
        this.createTinyURLService = createTinyURLService;
        this.updateTinyURLService = updateTinyURLService;
        this.getOriginalURLService = getOriginalURLService;
        this.deleteTinyURLService = deleteTinyURLService;
    }

    @LogExecutionTime
    @PostMapping(value = "/tiny/url", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept-Version=v1")
    public ResponseEntity<TinyURLResponseTO> createTinyURL(@Valid @RequestBody CreateTinyURLRequestTO createTinyURLRequestTO) {
        TinyURLResponseTO tinyURLResponseTO = createTinyURLService.createTinyURL(createTinyURLRequestTO);
        LOGGER.info("Tiny URL created successfully for url: {} as {}", tinyURLResponseTO.originalURL(), tinyURLResponseTO.tinyURL());
        return new ResponseEntity<>(tinyURLResponseTO, HttpStatus.CREATED);
    }

    @LogExecutionTime
    @PutMapping(value = "/tiny/url", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            headers = "Accept-Version=v1")
    public ResponseEntity<TinyURLResponseTO> updateOriginalURL(@Valid @RequestBody UpdateOriginalURLRequestTO updateOriginalURLRequestTO) {
        TinyURLResponseTO tinyURLResponseTO = updateTinyURLService.updateOriginalURL(updateOriginalURLRequestTO);
        LOGGER.info("Original URL updated successfully for tiny URL: {} with {}", tinyURLResponseTO.tinyURL(), tinyURLResponseTO.originalURL());
        return new ResponseEntity<>(tinyURLResponseTO, HttpStatus.OK);
    }

    @LogExecutionTime
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

    @LogExecutionTime
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
