package com.wby0014.lock.redission;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @Description redission分布式锁
 * ①线程去获取锁，获取成功：执行lua脚本，保存数据到redis数据库。
 * ②线程去获取锁，获取失败：一直通过while循环尝试获取锁，获取成功后，执行lua脚本，保存数据到redis数据库。
 * 在分布式高并发的条件下，同一时刻只能有一个线程获得锁，这是最基本的一点。
 * 此时，如果客户端2来尝试加锁，执行了同样的一段lua脚本，会怎样？很简单，第一个if判断会执行“exists myLock”，发现myLock这个锁key已经存在了。接着第二个if判断，判断一下，myLock锁key的hash数据结构中，是否包含客户端2的ID，但是明显不是的，因为那里包含的是客户端1的ID。所以，客户端2会获取到pttl myLock返回的一个数字，这个数字代表了myLock这个锁key的剩余生存时间。比如还剩15000毫秒的生存时间。此时客户端2会进入一个while循环，不停的尝试加锁。
 *
 * @Date 2020/11/13 15:42
 * @Author wuby31052
 */
public class RedissionLock {

    @Autowired
    private Redisson redisson;

    private void testRedisson(String msg) {
        String lockKey = "test_lock_key";
        //获取锁
        RLock redLock = redisson.getLock(lockKey);
        try {
            //锁5秒钟
            redLock.lock(5, TimeUnit.SECONDS);
            //TODO 处理业务逻辑
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //最后释放锁
            if (null != redLock && redLock.isLocked()) {
                redLock.unlock();
            }
        }
    }
}
