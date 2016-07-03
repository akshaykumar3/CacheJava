import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by akshay.kumar1 on 03/07/16.
 */
public class Redis {

    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;
    private static JedisPool pool = null;

    public Redis() {
    }

    public static void addToRedis(String key, String value) {
        //get a jedis connection jedis connection pool
        Jedis jedis = pool.getResource();

        try {
            jedis.set(key, value);

        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public static String getFromRedis(String key) {
        //get a jedis connection jedis connection pool
        Jedis jedis = pool.getResource();

        String value = "";
        try {
            value = jedis.get(key);

        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                pool.returnResource(jedis);
        }
        return value;
    }

    public static void setTimeOut(String key, int timeInSeconds) {
        //get a jedis connection jedis connection pool
        Jedis jedis = pool.getResource();

        try {
            jedis.expire(key, timeInSeconds);

        } catch (JedisException e) {
            //if something wrong happen, return it back to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///it's important to return the Jedis instance to the pool once you've finished using it
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }

    public static void main(String[] args){

        //configure our pool connection
        pool = new JedisPool(redisHost, redisPort);
        
        addToRedis("abc", "123");
        System.out.println("Data from redis for abc = "+getFromRedis("abc"));
        setTimeOut("abc", 10);
    }
}
