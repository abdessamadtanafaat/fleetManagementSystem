package com.system.gestionautomobile.caching.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

public class RedisUtils {

    public static String serializeObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static <T> T deserializeObject(String data, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            return objectMapper.readValue(data, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void setex(Jedis jedis, String key, int seconds, Object object) {
        jedis.setex(key, seconds, serializeObject(object));
    }
    public static String get(Jedis jedis, String key) {
        return jedis.get(key);
    }
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


}
