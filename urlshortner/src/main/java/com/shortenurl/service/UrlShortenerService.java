package com.shortenurl.service;

import com.shortenurl.model.ShortUrl;
import com.shortenurl.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
public class UrlShortenerService {
    private final ShortUrlRepository shortUrlRepository;

    public UrlShortenerService(ShortUrlRepository shortUrlRepository){
        this.shortUrlRepository = shortUrlRepository;
    }

    public String shortenURL(String longUrl){
        //This will search in the repository if there is already a shortenedURL
        //present for the longURL
        Optional<ShortUrl> existingUrl = shortUrlRepository.findByLongUrl(longUrl);
        if(existingUrl.isPresent()){
            return existingUrl.get().getShortUrl();
        }
        //This will allow URL for 64 ^ 6 = 68.7 billion unique URLs
        String shortUrl = Base64.getUrlEncoder().encodeToString(longUrl.getBytes(StandardCharsets.UTF_8)).substring(0,6);
        ShortUrl newUrl = new ShortUrl(null, longUrl,shortUrl);
        shortUrlRepository.save(newUrl);
        return  shortUrl;
    }

    public String getLongUrl(String shortUrl){
        return  shortUrlRepository.findByShortUrl(shortUrl).map(ShortUrl::getLongUrl).orElse(null);
    }


}
