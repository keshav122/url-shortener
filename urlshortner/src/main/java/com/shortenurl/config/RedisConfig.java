package com.shortenurl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        //LettuceConnectionFactory is an asynchronous Redis client
        //For synchronous Redis client use JedisConnectionFactory
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String,String> redisTemplate() {
   RedisTemplate<String,String> template = new RedisTemplate<>();
   template.setConnectionFactory(redisConnectionFactory());
   template.setKeySerializer(new StringRedisSerializer());
   template.setValueSerializer(new StringRedisSerializer());
   return template;
    }


}
