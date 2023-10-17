package com.mongo.manager.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public class ObjectUtils {

    public static <T extends Cloneable> T getClone(T obj) {
        try {
            Method clone = obj.getClass().getMethod("clone");
            return (T) clone.invoke(obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断对象是否为NULL
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj){
        return obj == null;
    }

    /**
     * 判断对象是否不为NULL
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj){
        return obj != null;
    }

    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if (obj == null){
            return true;
        }

        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    /**
     * 判断对象是否不为空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }

}
