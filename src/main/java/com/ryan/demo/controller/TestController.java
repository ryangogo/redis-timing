package com.ryan.demo.controller;


import com.ryan.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * describe：
 * Created with IDEA
 * author:ryan
 * Date:2018/10/4
 * Time:下午3:40
 */
@Controller
@RequestMapping("/demo/")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private JedisPool jedisPool;

    /**
     * 测试是否能成功连接db（与本demo无关）
     *
     * @return
     */
    @RequestMapping("test")
    @ResponseBody
    public String first() {
        return testService.getMapperDemo();
    }

    /**
     * 测试redis是否连接成功，如果成功的话会返回pong
     *
     * @return
     */
    @RequestMapping("testJedis")
    @ResponseBody
    public String second() {
        Jedis jedis = jedisPool.getResource();
        return jedis.ping();
    }

    /**
     * 向redis中存值，并且设置过期时间
     *
     * @return
     */
    @RequestMapping("testJedis2")
    @ResponseBody
    public String third() {
        Jedis jedis = jedisPool.getResource();
        jedis.set("notify", "author：ryan");
        //设置过期时间
        jedis.expire("notify", 10);
        return "向redis中添加数据成功";
    }

    /**
     * 测试向redis中存入的值是否还存在
     *
     * @return
     */
    @RequestMapping("testJedis3")
    @ResponseBody
    public String fourth() {
        Jedis jedis = jedisPool.getResource();
        return jedis.get("notify");
    }


}
