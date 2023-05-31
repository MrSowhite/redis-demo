package com.lqm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lqm.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
public class RedisStringTests {

    // 2 hot
    // StringRedisTemplate类的key和value的序列化方式默认为String方式，省去了自定义RedisTemplate的过程
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testTemplate() {
        stringRedisTemplate.opsForValue().set("name", "白豆腐");
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println("name=" + name);
    }

    // springMVC中默认使用的json处理工具
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testUser() throws JsonProcessingException {
        User user = new User("景天", 27);
        // 手动序列化
        String json = mapper.writeValueAsString(user);

        stringRedisTemplate.opsForValue().set("user:200", json);
        String jsonUser = stringRedisTemplate.opsForValue().get("user:200");
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println("user1=" + user1);
    }

    @Test
    void testHash(){
        stringRedisTemplate.opsForHash().put("user:400", "name", "胖迪");
        stringRedisTemplate.opsForHash().put("user:400", "age", "26");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println("entries=" + entries);
    }
}
