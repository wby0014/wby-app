package com.example.wby.common.memorycache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 内存缓存的实现，可设置超时时间，不支持集群，请勿缓存过大的数据
 */
public class MemoryStoreClient implements IMemoryCache {
    private static SimpleMemoryCache<String, MemoryStoreClient.TimeObject> dataStore = new SimpleMemoryCache(16384);
    private static final long NEVER_EXPIRE = 946080000000L;
    private static long checkTime = 0L;
    private static final long CHECK_PERIOD = 30000L;
    private String keyPrefix = "";

    @Override
    public String get(String key) {
        this.isNeedCheck();
        MemoryStoreClient.TimeObject value = dataStore.get(this.keyPrefix + "_" + key);
        return value != null && !value.isExpire() ? String.valueOf(value.getData()) : null;
    }

    @Override
    public boolean set(String key, String value) {
        this.setObject(key, value);
        return true;
    }

    @Override
    public boolean set(String key, String value, long exp) {
        this.setObject(key, value, exp);
        return true;
    }

    @Override
    public boolean remove(String key) {
        dataStore.remove(this.keyPrefix + "_" + key);
        return true;
    }

    @Override
    public Object getObject(String key) {
        this.isNeedCheck();
        MemoryStoreClient.TimeObject value = dataStore.get(this.keyPrefix + "_" + key);
        return value != null && !value.isExpire() ? value.getData() : null;
    }

    @Override
    public boolean setObject(String key, Object value) {
        MemoryStoreClient.TimeObject data = new MemoryStoreClient.TimeObject(value, NEVER_EXPIRE);
        data.setUpdateTime(System.currentTimeMillis());
        dataStore.put(this.buildKey(key), data);
        return true;
    }

    @Override
    public boolean setObject(String key, Object value, long exp) {
        MemoryStoreClient.TimeObject data = new MemoryStoreClient.TimeObject(value, exp);
        data.setUpdateTime(System.currentTimeMillis());
        dataStore.put(this.buildKey(key), data);
        return true;
    }

    @Override
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    @Override
    public void clear() {
        dataStore.clear();
    }

    private String buildKey(String key) {
        return this.keyPrefix != null ? this.keyPrefix + "_" + key : key;
    }

    private boolean isNeedCheck() {
        long now = System.currentTimeMillis();
        if (now - checkTime > CHECK_PERIOD) {
            MemoryStoreClient.ExpireDataCleaner cleaner = new MemoryStoreClient.ExpireDataCleaner();
            cleaner.start();
            checkTime = now;
            return true;
        } else {
            return false;
        }
    }

    static class ExpireDataCleaner extends Thread {

        private void putExpireData2List(Map<String, TimeObject> dataMap, List<String> container) {
            if (dataMap != null && dataMap.size() >= 1) {
                Iterator var3 = dataMap.entrySet().iterator();

                while (var3.hasNext()) {
                    Map.Entry<String, TimeObject> entry = (Map.Entry) var3.next();
                    String key = entry.getKey();
                    MemoryStoreClient.TimeObject to = entry.getValue();
                    if (to.isExpire()) {
                        container.add(key);
                    }
                }

            }
        }

        @Override
        public void run() {
            List<String> container = new ArrayList();
            Map<String, MemoryStoreClient.TimeObject> edenMap = MemoryStoreClient.dataStore.getEdenMap();
            Map<String, MemoryStoreClient.TimeObject> longTermMap = MemoryStoreClient.dataStore.getLongTermMap();
            this.putExpireData2List(edenMap, container);
            this.putExpireData2List(longTermMap, container);
            MemoryStoreClient.dataStore.remove(container);
        }
    }

    static class TimeObject {
        private long updateTime = -1L;
        private long expire = 0L;
        private Object data;

        public boolean isExpire() {
            long now = System.currentTimeMillis();
            return Math.abs(now - this.updateTime) > this.expire;
        }

        public TimeObject(Object data) {
            this.data = data;
        }

        public TimeObject(Object data, long expire) {
            this.data = data;
            this.expire = expire;
        }

        public long getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public long getExpire() {
            return this.expire;
        }

        public void setExpire(long expire) {
            this.expire = expire;
        }

        public Object getData() {
            return this.data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(this.data);
        }
    }
}
