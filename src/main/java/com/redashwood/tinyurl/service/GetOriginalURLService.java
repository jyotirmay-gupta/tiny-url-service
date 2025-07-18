package com.redashwood.tinyurl.service;

import com.redashwood.tinyurl.dto.TinyURLResponseTO;

public interface GetOriginalURLService {

    TinyURLResponseTO getOriginalURL(String tinyURL);
}
