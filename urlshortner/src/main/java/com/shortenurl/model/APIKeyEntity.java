package com.shortenurl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "api_keys")
@Data //Lombok to generate getters and setters
@NoArgsConstructor
@AllArgsConstructor
public class APIKeyEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String apiKey;

    @Column(nullable = false)
    private boolean isActive;

    private String owner;

}
