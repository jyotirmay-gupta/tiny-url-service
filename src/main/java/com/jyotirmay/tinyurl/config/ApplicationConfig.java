package com.jyotirmay.tinyurl.config;

import com.jyotirmay.tinyurl.entity.repository.TinyURLRepository;
import com.jyotirmay.tinyurl.generator.TinyURLGenerator;
import com.jyotirmay.tinyurl.generator.impl.DistributedTinyURLGenerator;
import com.jyotirmay.tinyurl.service.CreateTinyURLService;
import com.jyotirmay.tinyurl.service.DeleteTinyURLService;
import com.jyotirmay.tinyurl.service.GetOriginalURLService;
import com.jyotirmay.tinyurl.service.UpdateOriginalURLService;
import com.jyotirmay.tinyurl.service.impl.CreateTinyURLServiceImpl;
import com.jyotirmay.tinyurl.service.impl.DeleteTinyURLServiceImpl;
import com.jyotirmay.tinyurl.service.impl.GetOriginalURLServiceImpl;
import com.jyotirmay.tinyurl.service.impl.UpdateOriginalURLServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    TinyURLGenerator configureTinyURLGenerator() {
        return new DistributedTinyURLGenerator();
    }

    @Bean
    CreateTinyURLService configureCreateTinyURLService(TinyURLRepository tinyURLRepository, TinyURLGenerator tinyURLGenerator) {
        return new CreateTinyURLServiceImpl(tinyURLRepository, tinyURLGenerator);
    }

    @Bean
    UpdateOriginalURLService configureUpdateTinyURLService(TinyURLRepository tinyURLRepository) {
        return new UpdateOriginalURLServiceImpl(tinyURLRepository);
    }

    @Bean
    GetOriginalURLService configureGetOriginalURLService(TinyURLRepository tinyURLRepository) {
        return new GetOriginalURLServiceImpl(tinyURLRepository);
    }

    @Bean
    DeleteTinyURLService configureDeleteTinyURLService(TinyURLRepository tinyURLRepository) {
        return new DeleteTinyURLServiceImpl(tinyURLRepository);
    }
}
