package com.ryan.demo.redis;

import redis.clients.jedis.JedisPubSub;

/**
 * describe：获取redis发布以及订阅的信息
 * Created with IDEA
 * author:ryan
 * Date:2018/10/5
 * Time:下午12:49
 */
public class KeyExpiredListener extends JedisPubSub {

    /**
     * 获取redis的发布信息
     *
     * @param pattern
     * @param subscribedChannels
     */
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
    }

    /**
     * 获取redis的订阅信息
     *
     * @param pattern
     * @param channel
     * @param message redis中的key
     */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage pattern " + pattern + " " + channel + " " + message);
    }


}
