package com.jyotirmay.tinyurl.service;

import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;

public interface DeleteTinyURLService {

    TinyURLResponseTO deleteTinyURL(String tinyURL);
}
