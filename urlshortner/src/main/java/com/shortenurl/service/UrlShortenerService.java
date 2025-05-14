package com.shortenurl.service;

import com.shortenurl.model.ShortUrl;
import com.shortenurl.repository.ShortUrlRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlShortenerService {
    private final ShortUrlRepository shortUrlRepository;

    public UrlShortenerService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Cacheable(value = "shortenedUrls", key = "#longUrl")
    public String shortenURL(String longUrl) {
        //This will search in the repository if there is already a shortenedURL
        //present for the longURL

        Optional<ShortUrl> existingUrl = shortUrlRepository.findByLongUrl(longUrl);
        if (existingUrl.isPresent()) {
            return existingUrl.get().getShortUrl();
        }
        String shortUrl;
        do {
            shortUrl = generateUniqueShortUrl(); // Ensure unique URL
        } while (shortUrlRepository.existsByShortUrl(shortUrl));

        ShortUrl newUrl = new ShortUrl(null, longUrl, shortUrl);
        shortUrlRepository.save(newUrl);
        return shortUrl;
    }

    // New Unique URL Generator
    public String generateUniqueShortUrl() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 6);
    }

    @CacheEvict(value = "shortenedUrls", key = "#longUrl")
    public boolean deleteShortUrl(String longUrl) {
        Optional<ShortUrl> existingUrl = shortUrlRepository.findByLongUrl(longUrl);
        if (existingUrl.isPresent()) {
            shortUrlRepository.delete(existingUrl.get());
            return true;
        }
        return false;
    }

    @CacheEvict(value = "shortenedUrls", allEntries = true)
    public void clearCache() {
        System.out.println("Cache cleared manually via REST call");
    }

    @Scheduled(fixedRate = 86400000) // 1 day
    @CacheEvict(value = "shortenedUrls", allEntries = true)
    public void clearCacheScheduled() {
        System.out.println("Cache cleared automatically every 24 hours!");
    }

    public String getLongUrl(String shortUrl) {
        return shortUrlRepository.findByShortUrl(shortUrl).map(ShortUrl::getLongUrl).orElse(null);
    }

}
