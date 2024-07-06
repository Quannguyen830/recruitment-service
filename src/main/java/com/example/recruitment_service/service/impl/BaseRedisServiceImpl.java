package com.example.recruitment_service.service.impl;

import com.example.recruitment_service.service.BaseRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class BaseRedisServiceImpl implements BaseRedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, Object> hashOperations;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setTimeToLive(String key, long timeOutInDays) {
        redisTemplate.expire(key, timeOutInDays, TimeUnit.DAYS);
    }

    @Override
    public void hashSet(String key, String field, Object value) {
        hashOperations.put(key, field, value);
    }

    @Override
    public boolean hashExists(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Map<String, Object> getFields(String key) {
        return hashOperations.entries(key);
    }

    @Override
    public Object hashGet(String key, String field) {
        return hashOperations.get(key, field);
    }

    @Override
    public List<Object> hashGetByFieldPrefix(String key, String fieldPrefix) {
        List<Object> results = new ArrayList<>();
        Map<String, Object> entries = hashOperations.entries(key);
        entries.forEach((k, v) -> {
            if (k.startsWith(fieldPrefix)) {
                results.add(v);
            }
        });
        return results;
    }

    @Override
    public Set<String> getFieldPrefixes(String key) {
        Set<String> prefixes = new HashSet<>();
        Map<String, Object> entries = hashOperations.entries(key);
        entries.keySet().forEach(field -> {
            int index = field.indexOf(':');
            if (index != -1) {
                prefixes.add(field.substring(0, index));
            }
        });
        return prefixes;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delete(String key, String field) {
        hashOperations.delete(key, field);
    }

    @Override
    public void delete(String key, List<String> fields) {
        hashOperations.delete(key, (Object) fields.toArray(new String[0]));
    }
}

