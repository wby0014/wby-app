package com.example.wby.plugin.frame.process;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Date 2020/7/31 17:15
 * @Author wuby31052
 */
public class ProcessCache {

    private static final ConcurrentHashMap<String, Map<String, List<Method>>> CACHE = new ConcurrentHashMap<>();

    public static List<Method> getProcessByType(Class<?> clazz, String type) {
        List<Method> methods = new ArrayList<>();
        if (CACHE.containsKey(clazz.getName())) {
            Map<String, List<Method>> map = CACHE.get(clazz.getName());
            if (Objects.nonNull(map)) {
                List<Method> list = map.get(type);
                if (Objects.nonNull(list) && list.size() > 0) {
                    methods.addAll(list);
                }
            }
        }
        return methods;
    }

    public static void putProcesses(Class<?> clazz, Map<String, List<Method>> map) {
        CACHE.put(clazz.getName(), map);
    }
}
