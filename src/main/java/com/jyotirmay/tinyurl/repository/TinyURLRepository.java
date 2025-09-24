package com.jyotirmay.tinyurl.repository;

import com.jyotirmay.tinyurl.entity.TinyURLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TinyURLRepository extends JpaRepository<TinyURLEntity, Integer> {

    @Query("SELECT COALESCE(MAX(t.tinyUrlId), 0) FROM TinyURLEntity t")
    long findMaxTinyUrlId();
}
