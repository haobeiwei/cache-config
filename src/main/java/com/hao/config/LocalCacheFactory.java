package com.hao.config;

public class LocalCacheFactory implements CacheFactory{


    @Override
    public Cacheable create(Config cfg) {
        return new LocalCache();
    }
}
