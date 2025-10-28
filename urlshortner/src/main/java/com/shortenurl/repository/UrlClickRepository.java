package com.shortenurl.repository;

import com.shortenurl.model.ShortUrl;
import com.shortenurl.model.UrlClick;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlClickRepository extends JpaRepository<UrlClick, Long> {
    long countByShortCode(String shortCode);
}
