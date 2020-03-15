package com.hao.config;

/**
 * 创建统一的接口Cacheable,构建常用缓存使用方法
 * 然后用工厂模式 提供cache的实例化
 * */
public interface Cacheable {

    String get(String key);

    void set(String key, String  value, int ttl);

    void delete(String key);

    boolean exist(String key);
}

