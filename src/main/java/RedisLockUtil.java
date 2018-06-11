import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Collections;

/**
 * 分布式redis锁实现
 */
public class RedisLockUtil {

    private static final Jedis DEFAULT_CLIENT = RedisClient.getInstance();
    private static final String LOCK_SUCCESS="OK";
    private static final Long RELEASE_SUCCESS=1L;
    private static final Long EXPIRE_SUCCESS=1L;

    /**
     * 加锁
     * @param client  可自己传入redis-client，如果是null，则用默认的client
     * @param lockId  当前锁的key值
     * @param requestId 当前加锁的任务的请求标识，可以用线程id，或者分布式唯一id，
     *                   可以保证全局唯一即可，作为当前锁的value值使用，用作删除或
     *                   延时是判断是否是持有锁的任务
     * @param second  过期时间，避免锁未正常释放时形成死锁
     * @return true：加锁成功，flase：加锁失败
     */
    public static boolean lock(Jedis client, String lockId, String requestId,int second) {
        if (client == null)
            client = DEFAULT_CLIENT;
//        client.setnx(lockId,"1");
//        client.expire(lockId,60);
        //加锁和超时设置分为了两步，没法保证原子性，所以可以直接用下面的命令
        String rst = client.set(lockId,requestId,"nx","ex",second);
        if (LOCK_SUCCESS.equals(rst)) {
            return true;
        }
        return false;
    }

    /**
     * 释放锁
     * @param client  可自己传入redis-client，如果是null，则用默认的client
     * @param lockId  当前锁的key值
     * @param requestId 请求标识，删除前判断是否是持有锁的任务
     * @return true：加锁成功，flase：加锁失败
     */
    public static boolean unlock(Jedis client, String lockId, String requestId) {
        if (client == null)
            client = DEFAULT_CLIENT;
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                "then return redis.call('del', KEYS[1]) " +
                "else return 0 end";
        Object rst = client.eval(luaScript, Collections.singletonList(lockId), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(rst)) {
            return true;
        }
        return false;
    }

    /**
     * 延迟加锁时间
     * @param client 可自己传入redis-client，如果是null，则用默认的client
     * @param lockId  当前锁的key值
     * @param requestId 请求标识，延时前判断是否是持有锁的任务
     * @param second 申请延迟的时间
     * @return true：加锁成功，flase：加锁失败
     */
    public static boolean expired(Jedis client, String lockId, String requestId, int second) {
        if (client == null)
            client = DEFAULT_CLIENT;
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                "then return redis.call('expire',KEYS[1],ARGV[2]) " +
                "else return 0 end";
        Object rst = client.eval(luaScript,Collections.singletonList(lockId), Arrays.asList(requestId,String.valueOf(second)));
        if (EXPIRE_SUCCESS.equals(rst)) {
            return true;
        }
        return false;
    }
}

class RedisClient{
    private static volatile Jedis client = null;
    static Jedis getInstance(){
        String host = "127.0.0.1";
        int port = 6379;
        synchronized (RedisClient.class) {
            if (client == null) {
                client = new Jedis(host,port);
            }
        }
        return  client;
    }
}
