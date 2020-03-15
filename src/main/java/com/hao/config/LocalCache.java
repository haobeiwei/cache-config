package com.hao.config;

import java.util.Calendar;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存构建
 * 本地缓存的过期策略，可以设置一个线程，异步清理缓存池
 * */
public class LocalCache implements Cacheable{

    private Map<String, Data> map = new ConcurrentHashMap<>();

    public LocalCache(){

    }
    @Override
    public String get(String key) {

        Data data = map.get(key);
        //主动检查数据是否过期
        if(isExpired(data)){
            delete(key);
            return null;
        }
        return data.getValue();
    }

    @Override
    public void set(String key, String value, int ttl) {

    }

    @Override
    public void delete(String key) {

    }

    @Override
    public boolean exist(String key) {
        return false;
    }

    private boolean isExpired(Data data){
        if(data == null){
            return true;
        }

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(data.getTtl());

        if(c.before(Calendar.getInstance())){
            return true;
        }
        return false;
    }

    //构造函数中迪庆定时器进行每分钟过期检查
    class ExpiringChecker extends TimerTask {
        @Override
        public void run() {
            map.forEach((k, v) -> {
                if(isExpired(v)){
                    delete(k);
                }
            });
        }
    }

    class Data {
        private String value;

        private long ttl;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public long getTtl() {
            return ttl;
        }

        public void setTtl(long ttl) {
            this.ttl = ttl;
        }
    }
}
