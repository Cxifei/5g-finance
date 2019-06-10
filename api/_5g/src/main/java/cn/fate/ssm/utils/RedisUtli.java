package cn.fate.ssm.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtli {

    private static JedisPool jedisPool = new JedisPool();

    public static boolean addString(String key,String value){
        Jedis jedis = jedisPool.getResource();
        String set = jedis.set(key, value);
        jedis.close();
        return "ok".equalsIgnoreCase(set);
    }
    public static String getString(String key){
        Jedis resource = jedisPool.getResource();
        String s = resource.get(key);
        resource.close();
        return s;
    }

    public static boolean del(String key){
        Jedis resource = jedisPool.getResource();
        Long del = resource.del(key);
        return del>0;
    }
}
