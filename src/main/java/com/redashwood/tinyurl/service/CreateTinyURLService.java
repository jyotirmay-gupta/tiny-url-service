package com.redashwood.tinyurl.service;

import com.redashwood.tinyurl.dto.CreateTinyURLRequestTO;
import com.redashwood.tinyurl.dto.TinyURLResponseTO;

public interface CreateTinyURLService {

    TinyURLResponseTO createTinyURL(CreateTinyURLRequestTO createTinyURLRequestTO);
}
