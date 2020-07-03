package com.example.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class redisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //封装
    public void set(String key,Object object,Long time){
        //让该方法支持多种数据类型的
        if(object instanceof String){
            String value=(String) object;
            stringRedisTemplate.opsForValue().set(key,value);
        }
        if(object instanceof Set){
            Set<String> valueSet = (Set<String>) object;
            for (String string : valueSet) {
                stringRedisTemplate.opsForValue().set(key, string);
            }
        }
        //设置有效期
        if(time!=null) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }

    }
    public void setString(String key,Object object){
        String value=(String) object;
        stringRedisTemplate.opsForValue().set(key,value);
    }
    public void setSet(String key,Object object) {
        Set<String> valueSet = (Set<String>) object;
        for (String string : valueSet) {
            stringRedisTemplate.opsForValue().set(key, string);
        }
    }
    public String get(String key) {
        String value=stringRedisTemplate.opsForValue().get(key);
        return value;
    }
}
