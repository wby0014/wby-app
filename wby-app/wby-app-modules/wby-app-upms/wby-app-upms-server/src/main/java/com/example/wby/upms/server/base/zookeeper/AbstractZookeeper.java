package com.example.wby.upms.server.base.zookeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author wubinyu
 * @date 2019/9/25 14:24.
 */
@Slf4j
public abstract class AbstractZookeeper implements Watcher {

    public ZooKeeper zooKeeper;
    /**
     * 超时时间
     */
    private static final int SESSION_TIME_OUT = 2000;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("Watch received event");
            countDownLatch.countDown();
        }
    }

    /**
     * 连接
     *
     * @param host
     * @throws Exception
     */
    public void connect(String host) throws Exception {
        zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this::process);
        countDownLatch.await();
        System.out.println("zookeeper connection success");
    }

    /**
     * 关闭连接
     *
     * @throws InterruptedException
     */
    public void closeConnection() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }

    public static void main(String[] args) throws Exception {
        ZookeeperOperator zookeeperOperator = new ZookeeperOperator();
        zookeeperOperator.connect("10.19.160.225");

        List<String> children = zookeeperOperator.getChildren("/");
        System.out.println(children);
        String testNode = "zookeeper的Java API测试";
        zookeeperOperator.createNode("/root", "我是root节点");
        zookeeperOperator.createNode("/root/test", testNode);
        System.out.println(zookeeperOperator.getChildrenNum("/root/test"));
        System.out.println(zookeeperOperator.getData("/root/test"));
        System.out.println(zookeeperOperator.getCTime("/root/test"));
        System.out.println(zookeeperOperator.getChildren("/"));
        zookeeperOperator.closeConnection();
    }
}
