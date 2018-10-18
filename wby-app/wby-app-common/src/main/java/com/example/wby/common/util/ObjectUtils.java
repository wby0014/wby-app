package com.example.wby.common.util;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectUtils {
    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || (obj.getClass().isArray() ? Array.getLength(obj) == 0 : (obj instanceof CharSequence ? ((CharSequence) obj).length() == 0 : (obj instanceof Collection ? ((Collection) obj).isEmpty() : (obj instanceof Map ? ((Map) obj).isEmpty() : false))));
    }


    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isFileExist(File file) {
        return file != null && file.exists();
    }

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}

	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

    public static boolean isEmpty(Integer obj) {
        return obj == null;
    }

    public static boolean isNotEmpty(Integer obj) {
        return !isEmpty(obj);
    }

}
