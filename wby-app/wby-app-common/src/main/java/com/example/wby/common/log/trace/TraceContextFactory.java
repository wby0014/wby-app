package com.example.wby.common.log.trace;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class TraceContextFactory {
    private static String nodeIp;
    private static String nodeName;
    private static ReadWriteLock lock = new ReentrantReadWriteLock(false);

    public static void setDTSIp(String ip) {
        setDTSParam(ip, null);
    }

    public static String getDTSIp() {
        return nodeIp;
    }

    public static String getDTSName() {
        return nodeName;
    }

    public static void setDTSParam(String ip, String name) {
        try {
            lock.writeLock().lock();
            if (ip != null && ip.length() > 0) {
                nodeIp = ip;
            }
            if (name != null && name.length() > 0) {
                nodeName = name;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static TraceContext createTraceContext() {
        TraceContext traceContext;
        try {
            lock.readLock().lock();
            TraceContext tc = new TraceContext();
            traceContext = tc;
        } finally {
            lock.readLock().unlock();
        }
        return traceContext;
    }

}
