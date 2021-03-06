package com.taotao.content.jedis;

public interface JedisClient {
    Long del(String key);
    String set(String key, String value);
    String get(String key);
    //判断key是否存在
    Boolean exists(String key);
    //设置key的过期时间 秒
    Long expire(String key, int seconds);
    //获取key的过期时间
    Long ttl(String key);
    //自增1
    Long incr(String key);
    //设置散列
    Long hset(String key, String field, String value);
    //获取散列
    String hget(String key, String field);
    //删除散列
    Long hdel(String key, String... field);

}
