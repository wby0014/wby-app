package com.example.wby.common.memorycache;


public interface IMemoryCache {

    void setKeyPrefix(String keyPrefix);

    boolean set(String key, String value);

    boolean set(String key, String value, long exp);

    String get(String key);

    boolean remove(String key);

    boolean setObject(String key, Object value);

    boolean setObject(String key, Object value, long exp);

    Object getObject(String key);

    void clear();

}
