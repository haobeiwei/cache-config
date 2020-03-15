package com.hao.config;

import redis.clients.jedis.Jedis;

/**
 * 分布式缓存构建类适配器模式
 * */
public class DistributedCacheAdaptor extends Jedis implements Cacheable{
    public DistributedCacheAdaptor(String host, int port) {
        super(host, port);
    }

    @Override
    public String get(String key) {
        super.client.get(key);
        return null;
    }

    @Override
    public void set(String key, String value, int ttl) {
        super.client.set(key, value);
        super.client.expire(key, ttl);

    }

    @Override
    public void delete(String key) {

    }

    @Override
    public boolean exist(String key) {
        return false;
    }
}
