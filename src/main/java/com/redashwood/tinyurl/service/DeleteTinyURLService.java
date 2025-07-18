package com.redashwood.tinyurl.service;

import com.redashwood.tinyurl.dto.TinyURLResponseTO;

public interface DeleteTinyURLService {

    TinyURLResponseTO deleteTinyURL(String tinyURL);
}
