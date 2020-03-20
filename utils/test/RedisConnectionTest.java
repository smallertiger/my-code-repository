import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.RedisConnection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class RedisConnectionTest {

    private RedisConnection redisConnection;

    @Before
    public void before() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //设置连接池最大连接数量
        jedisPoolConfig.setMaxTotal(50);
        //设置连接池最大空闲连接数量
        jedisPoolConfig.setMaxIdle(10);
        //设置连接池最小空闲连接数量
        jedisPoolConfig.setMinIdle(1);

        redisConnection = new RedisConnection();
        redisConnection.setIp("127.0.0.1");
        redisConnection.setPort(6379);
        //redisConnection.setPassword();
        redisConnection.setClientName("test");
        redisConnection.setTimeout(600);
        redisConnection.setJedisPoolConfig(jedisPoolConfig);

    }

    @Test
    public void testPutGet() {
        Jedis jedis = redisConnection.getJedis();

        try{
            jedis.select(0);
            jedis.set("name", "grace");
            Assert.assertTrue("grace".equals(jedis.get("name")));
        }catch(Exception e) {

        }finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }
}
