package com.ryan.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * describe：
 * Created with IDEA
 * author:ryan
 * Date:2018/10/5
 * Time:下午1:11
 */
@Component
public class RedisInitSubscrib implements InitializingBean, Runnable {

    private static Properties props;

    private static Logger logger = LoggerFactory.getLogger(RedisInitSubscrib.class);

    static {
        String fileName = "redis.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(RedisInitSubscrib.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        RedisInitSubscrib subscrib = new RedisInitSubscrib();
        Thread thread = new Thread(subscrib);
        thread.start();
    }


    /**
     * 这里我新开了一个线程去执行这段代码，因为这段代码如果在启动时直接执行会阻塞spring的初始化
     */
    @Override
    public void run() {
        Jedis jedis;
        String redisIp = props.getProperty("redis.ip");
        Integer redisHost = Integer.valueOf(props.getProperty("redis.port"));
        JedisPool pool = new JedisPool(new JedisPoolConfig(), redisIp, redisHost);
        jedis = pool.getResource();
        jedis.psubscribe(new KeyExpiredListener(), "__keyevent@0__:expired");//过期队列
    }
}
