package com.jyotirmay.tinyurl.service;

import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;

public interface GetOriginalURLService {

    TinyURLResponseTO getOriginalURL(String tinyURL);
}
