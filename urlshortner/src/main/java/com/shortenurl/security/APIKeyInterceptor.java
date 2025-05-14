package com.shortenurl.security;

import com.shortenurl.model.APIKeyEntity;
import com.shortenurl.repository.APIKeyRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.Optional;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class APIKeyInterceptor implements HandlerInterceptor {

    private APIKeyRepository apiKeyRepository;

    public APIKeyInterceptor(APIKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response, Object handler) throws Exception{
        System.out.println("Inside Interceptor...");  // Add this line for testing
        String apiKey = request.getHeader("X-API-KEY");
        if(apiKey == null || apiKey.isEmpty()){
            response.sendError(HttpStatus.UNAUTHORIZED.value(),"Missing API Key");
            return false;
        }
        Optional<APIKeyEntity> apiKeyEntity = apiKeyRepository.findByApiKey(apiKey);
        if(apiKeyEntity.isEmpty() || !apiKeyEntity.get().isActive()){
            response.sendError(HttpStatus.UNAUTHORIZED.value(),"Invalid or Inactive API Key");
            return false;
        }
        return true;
    }

}
