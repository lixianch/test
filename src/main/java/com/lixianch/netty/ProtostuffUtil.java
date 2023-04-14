package com.lixianch.netty;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by lixianch on 2023/4/10
 */
public class ProtostuffUtil {

    private static Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();

    public static <T> byte[] serialize(T obj) {
        Class<T> clazz = (Class<T>)obj.getClass();
        Schema<T> schema = getSchema(clazz);
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] data = null;
        try {
            data = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        }finally {
            buffer.clear();
        }
        return data;
    }

    public static <T> T deserialize(byte[] data, Class<T> clazz){
        Schema<T> schema = getSchema(clazz);
        T message = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, message, schema);

        return message;
    }
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) schemaCache.get(clazz);
        if(Objects.isNull(schema)){
            schema = RuntimeSchema.getSchema(clazz);
            if(!Objects.isNull(schema)){
                schemaCache.put(clazz, schema);
            }
        }

        return schema;
    }

}
