package com.shortenurl.repository;

import com.shortenurl.model.APIKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface APIKeyRepository extends JpaRepository<APIKeyEntity, Long> {
    Optional<APIKeyEntity> findByApiKey(String apiKey);
}
