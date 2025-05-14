package com.shortenurl.config;

import com.shortenurl.security.APIKeyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final APIKeyInterceptor apiKeyInterceptor;

    public WebConfig(APIKeyInterceptor apiKeyInterceptor) {
        this.apiKeyInterceptor = apiKeyInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry){
       registry.addInterceptor(apiKeyInterceptor).addPathPatterns("/api/url/shorten");
    }

}
