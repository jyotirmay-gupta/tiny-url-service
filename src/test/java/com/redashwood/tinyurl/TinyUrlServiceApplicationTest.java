/*
 * Copyright (c) 2025 Jyotirmay Gupta
 *
 * Project: Tiny URL
 * Description: This is a personal project by Jyotirmay Gupta that implements a
 * simplified URL shortening service, similar in concept to services like bit.ly or tinyurl.com.
 * It allows long URLs to be converted into compact, easy-to-share short URLs.
 *
 * This code is intended for educational and personal use, demonstrating core backend
 * concepts such as encoding algorithms, database storage, and URL mapping.
 *
 * Licensed under the Apache License Version 2.0. See LICENSE file for more details.
 */
package com.redashwood.tinyurl;

import com.redashwood.tinyurl.repository.TinyURLRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class
})
@ActiveProfiles("test")
class TinyUrlServiceApplicationTest {

    @MockitoBean
    private TinyURLRepository tinyURLRepository;

    @Test
    void contextLoads() {
        // Test context load on startup
    }
}
