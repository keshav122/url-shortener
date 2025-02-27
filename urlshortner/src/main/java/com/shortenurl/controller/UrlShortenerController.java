package com.shortenurl.controller;

import com.shortenurl.service.UrlShortenerService;
import org.springframework.http.HttpStatus;
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

    //Sample URL : http://localhost:8080/api/url/shorten
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody HashMap<String, String> request){
        if(!request.containsKey("longUrl") || request.get("longUrl").isEmpty()) {
            return ResponseEntity.badRequest().body("longUrl is required");
        }
        String longUrl = request.get("longUrl");
        String shortUrl = urlShortenerService.shortenURL(longUrl);
        return ResponseEntity.ok(shortUrl);
    }

    //Sample URL : http://localhost:8080/api/url/aHR0cH
    @GetMapping("/{shortUrl}")
    public String getLongUrl(@PathVariable String shortUrl){
        return urlShortenerService.getLongUrl(shortUrl);
    }

    //Sample URL : http://localhost:8080/api/url/delete?longUrl=https://example.com
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteShortUrl(@RequestParam String longUrl){
        boolean deleted = urlShortenerService.deleteShortUrl(longUrl);
        if (deleted) {
            return ResponseEntity.ok("URL deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("URL not found");
        }
    }

    //Sample URL : http://localhost:8080/api/url/cache/clear
    @DeleteMapping("/cache/clear")
    public ResponseEntity<String> clearCache(){
       urlShortenerService.clearCache();
       return ResponseEntity.ok("Cache cleared successfully");
    }


}

