/*
 *
 *  * Copyright (c) 2025 Jyotirmay Gupta
 *  *
 *  * Project: Tiny URL
 *  * Description: This is a personal project by Jyotirmay Gupta that implements a
 *  * simplified URL shortening service, similar in concept to services like bit.ly or tinyurl.com.
 *  * It allows long URLs to be converted into compact, easy-to-share short URLs.
 *  *
 *  * This code is intended for educational and personal use, demonstrating core backend
 *  * concepts such as encoding algorithms, database storage, and URL mapping.
 *  *
 *  * Licensed under the Apache License Version 2.0. See LICENSE file for more details.
 *
 *
 */
package com.jyotirmay.tinyurl.controller;

import com.jyotirmay.tinyurl.dto.CreateTinyURLRequestTO;
import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.dto.UpdateOriginalURLRequestTO;
import com.jyotirmay.tinyurl.service.CreateTinyURLService;
import com.jyotirmay.tinyurl.service.DeleteTinyURLService;
import com.jyotirmay.tinyurl.service.GetOriginalURLService;
import com.jyotirmay.tinyurl.service.UpdateOriginalURLService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

@ExtendWith(MockitoExtension.class)
class TinyURLControllerTest {

    @InjectMocks
    private TinyURLController tinyURLController;

    @Mock
    private CreateTinyURLService createTinyURLService;

    @Mock
    private UpdateOriginalURLService updateOriginalURLService;

    @Mock
    private GetOriginalURLService getOriginalURLService;

    @Mock
    private DeleteTinyURLService deleteTinyURLService;

    @Test
    void givenValidCreateTinyURLTO_whenCreateTinyURLCalled_thenReturnsCreatedTinyURLResponse() {
        CreateTinyURLRequestTO createTinyURLRequestTO = new CreateTinyURLRequestTO("https://example.com");
        TinyURLResponseTO tinyURLResponseTO = new TinyURLResponseTO("https://example.com", "seri2s2as",
                true, Instant.now(), null);

        Mockito.when(createTinyURLService.createTinyURL(createTinyURLRequestTO)).thenReturn(tinyURLResponseTO);

        ResponseEntity<TinyURLResponseTO> responseEntity = tinyURLController.createTinyURL(createTinyURLRequestTO);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(tinyURLResponseTO, responseEntity.getBody());

        Mockito.verify(createTinyURLService, Mockito.times(1)).createTinyURL(createTinyURLRequestTO);
    }

    @Test
    void givenValidUpdateOriginalURLTO_whenUpdateOriginalURLCalled_thenReturnsUpdatedTinyURLResponse() {

        UpdateOriginalURLRequestTO updateOriginalURLTO = new UpdateOriginalURLRequestTO("https://example.com","seri2s2as");
        TinyURLResponseTO tinyURLResponseTO = new TinyURLResponseTO("https://example.com", "seri2s2as",
                true, Instant.now(), null);

        Mockito.when(updateOriginalURLService.updateOriginalURL(updateOriginalURLTO)).thenReturn(tinyURLResponseTO);

        ResponseEntity<TinyURLResponseTO> responseEntity = tinyURLController.updateOriginalURL(updateOriginalURLTO);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(tinyURLResponseTO, responseEntity.getBody());

        //verifies that a specific method was called exactly once on the mocked object updateOriginalURLService during the test.
        Mockito.verify(updateOriginalURLService, Mockito.times(1)).updateOriginalURL(updateOriginalURLTO);

    }

    @Test
    void givenValidTinyURL_whenGetOriginalURLCalled_thenReturnsOriginalTinyURLResponse() {
        String tinyURL = "seri2s2as";
        TinyURLResponseTO tinyURLResponseTO = new TinyURLResponseTO("https://example.com", "seri2s2as",
                true, Instant.now(), null);

        Mockito.when(getOriginalURLService.getOriginalURL(tinyURL)).thenReturn(tinyURLResponseTO);

        ResponseEntity<TinyURLResponseTO> responseEntity = tinyURLController.getOriginalURL(tinyURL);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(tinyURLResponseTO, responseEntity.getBody());

        Mockito.verify(getOriginalURLService, Mockito.times(1)).getOriginalURL(tinyURL);
    }

    @Test
    void givenValidTinyURL_whenDeleteTinyURLCalled_thenReturnsDeletedTinyURLResponse() {

        String tinyURL = "seri2s2as";
        TinyURLResponseTO tinyURLResponseTO = new TinyURLResponseTO("https://example.com", "seri2s2as",
                false, Instant.now(), null);

        Mockito.when(deleteTinyURLService.deleteTinyURL(tinyURL)).thenReturn(tinyURLResponseTO);

        ResponseEntity<TinyURLResponseTO> responseEntity = tinyURLController.deleteTinyURL(tinyURL);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(tinyURLResponseTO, responseEntity.getBody());

        Mockito.verify(deleteTinyURLService, Mockito.times(1)).deleteTinyURL(tinyURL);
    }
}
