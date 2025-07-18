package com.redashwood.tinyurl.repository;

import com.redashwood.tinyurl.entity.TinyURLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TinyURLRepository extends JpaRepository<TinyURLEntity, Integer> {

    Optional<TinyURLEntity> findByTinyUrlAndActive(String tinyURL, boolean active);

    @Query("SELECT COALESCE(MAX(t.tinyUrlId), 0) FROM TinyURLEntity t")
    long findMaxTinyUrlId();
}
