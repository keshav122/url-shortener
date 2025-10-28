package com.shortenurl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "url_clicks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlClick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "short_code", nullable = false)
    private String shortCode;

    @Column(name =  "clicked_at", nullable = false)
    private java.time.LocalDateTime clickedAt = java.time.LocalDateTime.now();

    private String ipAddress;
    private String userAgent;

    public UrlClick(String shortUrl, String ip, String userAgent, java.time.LocalDateTime timestamp) {
        this.shortCode = shortUrl;
        this.ipAddress = ip;
        this.userAgent = userAgent;
        this.clickedAt = timestamp;
    }
}
