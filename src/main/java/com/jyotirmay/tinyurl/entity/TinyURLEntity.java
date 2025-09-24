package com.jyotirmay.tinyurl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tiny_url", schema = "tiny_schema")
public class TinyURLEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tiny_url_id")
    private int tinyUrlId;

    @Column(name = "original_url", nullable = false, columnDefinition = "TEXT")
    private String originalUrl;

    @Column(name = "tiny_url", nullable = false, unique = true, length = 100)
    private String tinyUrl;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_on", nullable = false, updatable = false)
    private OffsetDateTime createdOn;

    @Column(name = "updated_on")
    private OffsetDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdOn = now;
        this.updatedOn = now;
        this.active = true;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedOn = OffsetDateTime.now();
    }

    public int getTinyUrlId() {
        return tinyUrlId;
    }

    public void setTinyUrlId(int tinyUrlId) {
        this.tinyUrlId = tinyUrlId;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public OffsetDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}