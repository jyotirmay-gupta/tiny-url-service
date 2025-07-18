package com.redashwood.tinyurl.service;

import com.redashwood.tinyurl.dto.TinyURLResponseTO;
import com.redashwood.tinyurl.dto.UpdateOriginalURLRequestTO;

public interface UpdateOriginalURLService {

    TinyURLResponseTO updateOriginalURL(UpdateOriginalURLRequestTO updateOriginalURLRequestTO);
}
