package usts.pycro.redis7.demo;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-06 10:17 PM
 */
public class JedisDemo {
    public static void main(String[] args) {
        // 1.connection获得，通过指定ip和端口号
        Jedis jedis = new Jedis("192.168.37.130", 6379);
        // 2.指定访问服务器的密码
        jedis.auth("123456");
        // 3.获得了jedis客户端，可以像jdbc一样，访问redis
        System.out.println(jedis.ping());

        //keys
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

        //string
        jedis.set("k3", "hello,jedis");
        System.out.println(jedis.get("k3"));
        System.out.println(jedis.ttl("k3"));
        jedis.expire("k3", 20);

        //list
        jedis.lpush("list", "11", "12", "13");
        List<String> list = jedis.lrange("list", 0, -1);
        list.forEach(System.out::println);

        //set
        jedis.sadd("set1", "a", "b", "c");
        System.out.println(jedis.smembers("set1"));
        jedis.srem("set1", "b");
        System.out.println(jedis.smembers("set1").size());

        //hash
        jedis.hset("hs1", "name", "Pycro");
        System.out.println(jedis.hgetAll("hs1"));
        Map<String, String> map = new HashMap<>();
        map.put("age", "12");
        map.put("gender", "male");
        map.put("email", "123@qq.com");
        jedis.hset("hs1", map);
        System.out.println(jedis.hgetAll("hs1"));
        jedis.hdel("hs1", "age");
        System.out.println(jedis.hgetAll("hs1"));

        //zset
        jedis.zadd("zs1", 60, "z1");
        jedis.zadd("zs1", 70, "z2");
        jedis.zadd("zs1", 80, "z3");
        Map<String, Double> zMap = new HashMap<>();
        zMap.put("z4", 90.0);
        zMap.put("z5", 100.0);
        jedis.zadd("zs1", zMap);

        System.out.println(jedis.zrangeWithScores("zs1", 0, -1));
    }
}
