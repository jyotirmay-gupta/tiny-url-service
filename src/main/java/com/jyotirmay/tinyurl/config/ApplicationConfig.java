package com.jyotirmay.tinyurl.config;

import com.jyotirmay.tinyurl.repository.TinyURLRepository;
import com.jyotirmay.tinyurl.util.CounterBasedURLShortener;
import com.jyotirmay.tinyurl.util.URLShortener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    URLShortener configureURLShortener(TinyURLRepository tinyURLRepository) {
        return new CounterBasedURLShortener(tinyURLRepository);
    }


}
