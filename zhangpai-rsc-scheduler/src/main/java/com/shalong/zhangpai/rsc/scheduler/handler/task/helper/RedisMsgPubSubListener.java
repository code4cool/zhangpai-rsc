package com.shalong.zhangpai.rsc.scheduler.handler.task.helper;

import redis.clients.jedis.JedisPubSub;

/**
 * @FileName: RedisMsgPubSubListener.java
 * @Package com.shalong.zhangpai.rsc.scheduler.handler.task.helper
 * 
 * @author Huangyunjun
 * @created 2016年7月31日 下午3:50:52
 * 
 * Copyright 2015-2016 沙龙掌拍(北京)科技有限公司版权所有
 */
public class RedisMsgPubSubListener extends JedisPubSub {
    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }

    @Override
    public void unsubscribe(String... channels) {
        super.unsubscribe(channels);
    }

    @Override
    public void subscribe(String... channels) {
        super.subscribe(channels);
    }

    @Override
    public void psubscribe(String... patterns) {
        super.psubscribe(patterns);
    }

    @Override
    public void punsubscribe() {
        super.punsubscribe();
    }

    @Override
    public void punsubscribe(String... patterns) {
        super.punsubscribe(patterns);
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("channel:" + channel + " receives message :" + message);
        this.unsubscribe();
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
       // System.out.println("channel:" + channel + " is been subscribed:" + subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
       // System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);
    }
}