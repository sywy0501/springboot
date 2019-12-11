package com.cs.springboot;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.cs.springboot.redis.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        List<User> list = new ArrayList<>();
        User user = new User();

        user.getAge();
        user.setAge(1);
        user.setName("你好");
        list.add(user);
        stringRedisTemplate.opsForValue().set("test4", "看");
        //redisTemplate.opsForList().leftPush("int:list4", JSON.toJSONString(list));

        for (int i = 0; i < 10000; i++) {
            redisTemplate.opsForSet().add("key" + i, i);
        }
    }

}
