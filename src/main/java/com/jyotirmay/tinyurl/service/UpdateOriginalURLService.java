package com.jyotirmay.tinyurl.service;

import com.jyotirmay.tinyurl.dto.TinyURLResponseTO;
import com.jyotirmay.tinyurl.dto.UpdateOriginalURLRequestTO;

public interface UpdateOriginalURLService {

    TinyURLResponseTO updateOriginalURL(UpdateOriginalURLRequestTO updateOriginalURLRequestTO);

}
