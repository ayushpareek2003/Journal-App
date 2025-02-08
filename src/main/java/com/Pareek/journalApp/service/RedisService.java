package com.Pareek.journalApp.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T>  T get(String key,Class<T> resonseEntity){
        try {
            Object responseTemp = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseTemp.toString(), resonseEntity);
        }
        catch (Exception e) {
            log.error("Exception");

        }

        return null;
    }

    public void set(String key,Object value, Long expire){
        try{
            ObjectMapper mapper = new ObjectMapper();

            String jsonValue=mapper.writeValueAsString(value);

            redisTemplate.opsForValue().set(key,jsonValue, expire,TimeUnit.SECONDS);
        }
        catch (Exception e) {
            log.error("Exception");
        }
    }
}
