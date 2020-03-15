package com.hao.config;

import redis.clients.jedis.Jedis;

/**
 * 分布式缓存构建组合适配器模式
 * */
public class DistributedCache implements Cacheable{

    // 引入Jedis作为客户端  组合模式
    private static Jedis client;

    //构造函数中初始化jedis
    public DistributedCache(String host, int port){
        client = new Jedis(host, port);
    }

    //提供获取客户端的方法
    public Jedis getRedisClient(){
        return client;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void set(String key, String value, int ttl) {
        client.set(key, value);
        client.expire(key, ttl);

    }

    @Override
    public void delete(String key) {

    }

    @Override
    public boolean exist(String key) {
        return false;
    }
}
