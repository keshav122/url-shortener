package com.shortenurl.controller;

import com.shortenurl.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody HashMap<String, String> request){
        if(!request.containsKey("longUrl") || request.get("longUrl").isEmpty()) {
            return ResponseEntity.badRequest().body("longUrl is required");
        }
        String longUrl = request.get("longUrl");
        String shortUrl = urlShortenerService.shortenURL(longUrl);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortUrl}")
    public String getLongUrl(@PathVariable String shortUrl){
        return urlShortenerService.getLongUrl(shortUrl);
    }


}

