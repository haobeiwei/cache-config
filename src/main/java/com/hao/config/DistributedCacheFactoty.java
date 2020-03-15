package com.hao.config;

public class DistributedCacheFactoty implements CacheFactory{
    @Override
    public Cacheable create(Config cfg) {
        return new DistributedCache(cfg.getHost(), cfg.getPort());
    }
}
