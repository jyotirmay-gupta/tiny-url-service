package com.jyotirmay.tinyurl.service;

import com.jyotirmay.tinyurl.dto.CreateTinyURLRequestTO;
import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;

public interface CreateTinyURLService {

    TinyURLResponseTO createTinyURL(CreateTinyURLRequestTO createTinyURLRequestTO);
}
