package usts.pycro.redis7.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.List;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-06 10:51 PM
 */
public class LettuceDemo {
    public static void main(String[] args) {
        //1.使用构建器链式编程builder出RedisURI
        RedisURI uri = RedisURI.Builder
                .redis("192.168.37.130")
                .withPort(6379)
                .withAuthentication("default", "123456")
                .build();
        //2.创建连接客户端
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> conn = redisClient.connect();

        //3.通过conn创建操作的command
        RedisCommands<String, String> commands = conn.sync();

        //=====================================

        //keys
        List<String> keys = commands.keys("*");
        System.out.println("******************" + keys);

        //string
        commands.set("k5", "hello,lettuce");
        System.out.println("******************" + commands.get("k5"));

        //list
        System.out.println("******************" + commands.lrange("list", 0, -1));
        //hash
        System.out.println("******************" + commands.hgetall("hs1"));
        //set
        System.out.println("******************" + commands.smembers("set1"));
        //zset
        System.out.println("******************" + commands.zrange("zs1", 0, -1));
        //=====================================

        //4.各种关闭释放资源
        conn.close();
        redisClient.shutdown();
    }
}
