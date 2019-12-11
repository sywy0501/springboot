package com.cs.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.io.IOException;

/**
 * @author: ChuShi
 * @date: 2019/11/25 5:00 下午
 * @desc: 简单限流
 */
public class SimpleRateLimiter {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Jedis jedis;

    /**
     * @author: ChuShi
     * @date: 2019/11/28 10:17 上午
     * @param userId
     * @param actionKey
     * @param period
     * @param maxCount
     * @return: boolean
     * @desc: 滑动窗口限流，先添加再进行判断数量可能会导致判断失误
     */
    public boolean isActionAllowed(String userId,String actionKey,int period,int maxCount) throws IOException {
        String key  = String.format("hist:%s:%s",userId,actionKey);
        long nowTs = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        //multi是指事务，能保证一系列指令的源自顺序执行
        pipeline.multi();
        //记录行为
        //value和score都使用毫秒时间戳
        pipeline.zadd(key,nowTs,""+nowTs);
        //移除时间窗口之前的行为记录，剩下的都是时间窗口内的
        pipeline.zremrangeByScore(key,0,nowTs-period*1000);
        //获取窗口内的行为数量
        Response<Long> count = pipeline.zcard(key);
        //设置zset的过期时间，避免冷用户持续占用内存，每次添加都会更新key的过期时间
        //过期时间应该等于时间窗口的长度，最多宽限1s
        pipeline.expire(key,period+1);
        //批量执行
        pipeline.exec();
        pipeline.close();
        //比较数量是否超标
        return count.get()<=maxCount;
    }
}
