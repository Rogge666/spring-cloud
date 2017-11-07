package com.rogge.common.conf.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.lang.reflect.Method;

public class RedisSerializer {

    private static Log logger = LogFactory.getLog(RedisSerializer.class);

    public static final String CHARSET = "utf-8";

    public static <T extends Serializable> byte[] serialize(T t) throws RuntimeException {
        if (t == null) {
            return null;
        }
        byte[] bytes = null;
        try {
            if (t.getClass() == Integer.TYPE || t.getClass() == Integer.class
                    || t.getClass() == Long.TYPE || t.getClass() == Long.class
                    || t.getClass() == Short.TYPE || t.getClass() == Short.class
                    || t.getClass() == Double.TYPE || t.getClass() == Double.class
                    || t.getClass() == Float.TYPE || t.getClass() == Float.class
                    || t.getClass() == Byte.TYPE || t.getClass() == Byte.class
                    || t.getClass() == Character.TYPE || t.getClass() == Character.class
                    || t.getClass() == Boolean.TYPE || t.getClass() == Boolean.class
                    || t.getClass() == String.class) {
                bytes = (t + "").getBytes(CHARSET);
            } else {
                ByteArrayOutputStream baos = null;
                ObjectOutputStream oos = null;
                try {
                    baos = new ByteArrayOutputStream();
                    oos = new ObjectOutputStream(baos);
                    oos.writeObject(t);
                    bytes = baos.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (baos != null) {
                        baos.close();
                    }
                    if (oos != null) {
                        oos.close();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("序列化失败 " + e.getMessage(), e);
            // throw new NormalRuntimeException("序列化失败", e);
        }
        return bytes;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deserialize(byte[] bytes, Class<T> clazz) throws RuntimeException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        try {
            if (clazz == Integer.TYPE || clazz == Integer.class
                    || clazz == Long.TYPE || clazz == Long.class
                    || clazz == Short.TYPE || clazz == Short.class
                    || clazz == Double.TYPE || clazz == Double.class
                    || clazz == Float.TYPE || clazz == Float.class
                    || clazz == Byte.TYPE || clazz == Byte.class
                    || clazz == Character.TYPE || clazz == Character.class
                    || clazz == Boolean.TYPE || clazz == Boolean.class
                    || clazz == String.class) {

                String s = null;

                s = new String(bytes, CHARSET);

                if (clazz == String.class) {
                    return (T) s;
                } else if (clazz == Character.TYPE || clazz == Character.class) {
                    Character c = s.charAt(0);
                    return (T) c;
                } else {
                    Method method = clazz.getMethod("valueOf", String.class);
                    Object object = method.invoke(null, s);
                    return (T) object;
                }
            } else {
                ByteArrayInputStream bais = null;
                ObjectInputStream ois = null;
                try {
                    bais = new ByteArrayInputStream(bytes);
                    ois = new ObjectInputStream(bais);
                    Object object = ois.readObject();
                    return (T) object;
                } catch (Exception e) {
                    logger.error("反序列化失败 " + e.getMessage(), e);
                    // throw new NormalRuntimeException("反序列化失败", e);
                } finally {
                    if (ois != null) {
                        ois.close();
                    }
                    if (bais != null) {
                        bais.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
