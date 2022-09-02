package me.delivery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1500)
public class RedisConfig {

    @Value("${spring.redis.host}")
    public String host;

    @Value("${spring.redis.port}")
    public int port;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        var redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
      //  redisTemplate.setValueSerializer(new StringRedisSerializer());    //<String,String> 의 경우에 사용.
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(host);
        conf.setPort(port);
        //conf.setDatabase(); redis db 나눌때 사용.
        return new LettuceConnectionFactory(conf);
    }
}
