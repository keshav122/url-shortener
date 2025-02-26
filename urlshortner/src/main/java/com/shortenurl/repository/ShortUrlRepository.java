package com.shortenurl.repository;

import com.shortenurl.model.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortUrl(String shortUrl);
    Optional<ShortUrl> findByLongUrl(String longUrl);
}
