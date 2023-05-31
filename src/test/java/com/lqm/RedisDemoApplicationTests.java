package com.lqm;

import com.lqm.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testTemplate() {
        redisTemplate.opsForValue().set("name", "杨幂");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name=" + name);
    }

    @Test
    void testUser() {
        redisTemplate.opsForValue().set("user:100", new User("胡歌", 27));
        User user = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println("user=" + user);
    }

}
