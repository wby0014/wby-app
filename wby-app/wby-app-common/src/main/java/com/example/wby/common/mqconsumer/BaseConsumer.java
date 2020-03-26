package com.example.wby.common.mqconsumer;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 监听mq消息，先存放队列，用于缓解消息接收的瞬时压力
 */
public abstract class BaseConsumer {

    /**
     * 默认为不是持久化消息
     */
    private static final boolean DEFAULT_IS_PERSISTENT = false;

    /**
     * 默认为主题订阅模式
     */
    private static final boolean DEFAULT_IS_TOPIC = true;

    /**
     * 队列内的最大消息数量，超出后打印异常信息
     */
    private static final int MAX_CONTENT = 1500;

    /**
     * 单个批次的数据量
     */
    private static final int BATCH_COUNT = 200;

    /**
     * 存放消息的队列，用于缓解消息接受的瞬时压力，反之消费者被冲垮
     */
    protected BlockingDeque<String> queue = new LinkedBlockingDeque<>(MAX_CONTENT);

    /**
     * 线程池,用于并行处理消息
     */
    protected ThreadPoolTaskExecutor executor;

    /**
     * 通过子类注入这个工厂
     */
    //    protected SpringNotifyConnectionFactory factory;

    /**
     * 主题
     */
    protected String topic;
    /**
     * 是否主题
     */
    protected Boolean ourIsTopic;
    /**
     * 是否持久化
     */
    protected Boolean ourIsPersistent;


    private InnerConsumer consumer;

    /**
     * 锁
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 并行插入时的条件
     */
    private final Condition showTime = lock.newCondition();

    /**
     * 功能描述: 启动消费者
     */
    public void startConsumer() {
        //初始化参数信息，要将异常抛出去给业务的，所以不能放在try里面
        // 创建消费者
        consumer = new InnerConsumer();
        //        initParams();
        try {
            //            consumer.start();
            // 开启数据消费线程
            taskModule();
        } catch (Exception e) {
        }
    }


    private class InnerConsumer /*extends SpringNotifyConsumer*/ {

        /**
         * 功能描述: 监听消息
         * 为了提升消费能力，先将获取到的数据压如队列内部，如果队列中的数据量达到了一个批次的要求后直接进行批次处理
         * 如果数据没有达到一个批次的要求，由taskModule来定时处理
         * 这里会有一个小问题，子线程对应的消息复杂度可能会有不一样的地方
         * 可能会导致处理的最终结果并不一定完全按照消息接受的顺序
         * 如需要完全按照时间线处理消息，请采用串行处理，当一个消息处理完再处理下一个。
         */
        //        @Override
        public void onMessage(/*Message message*/) throws Exception {

            // 不管什么情况先入了队列再说
            queue.add("message.getData()");
            // 这个时候判断一下是否队列中的数据数量达到一个批次要求了
            if (queue.size() >= BATCH_COUNT) {
                List<String> temp = new ArrayList<>(BATCH_COUNT);
                // 倾泻数据到一个临时的集合中
                queue.drainTo(temp, BATCH_COUNT);
                // 消费数据,异步防阻塞
                executor.execute(() -> resolveMessage(temp));
            }
        }
    }

    /**
     * 功能描述: 监听队列中的消息，间隔一定时间去处理队列中的消息，和onMessage()方法内的积累一定条数后去处理相互呼应
     */
    public void taskModule() {
        executor.execute(() -> {
            for (; ; ) {
                // 5秒执行一次
                lock.lock();
                try {
                    showTime.await(5, TimeUnit.SECONDS);
                } catch (Exception e) {
                } finally {
                    lock.unlock();
                }
                // 这里必须要加上try-catch,避免由内部问题导致异常未捕捉然后整个循环退出，定时任务模块不执行
                try {
                    //当队列中确实有数据时才进行数据处理
                    if (queue.size() > 0) {
                        // 批量插入数据库
                        List<String> temp = new ArrayList<>(BATCH_COUNT);
                        queue.drainTo(temp, BATCH_COUNT);
                        resolveMessage(temp);
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    /**
     * 功能描述: 怎么处理数据需要自己实现
     */
    public abstract void resolveMessage(List<String> msg);

    /**
     * 功能描述: 程序退出前将队列中的缓存数据处理一下
     */
    @PreDestroy
    public void contextDestroyed() {
        try {
            //            consumer.stop();
        } catch (Exception e) {
        }
        // 批量插入数据库
        for (; queue.size() > 0; ) {
            List<String> list = new ArrayList<>(BATCH_COUNT);
            queue.drainTo(list, BATCH_COUNT);
            resolveMessage(list);
        }
    }

}
