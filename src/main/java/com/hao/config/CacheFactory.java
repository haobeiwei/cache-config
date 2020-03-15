package com.hao.config;

public interface CacheFactory {

    Cacheable create(Config cfg);

}
