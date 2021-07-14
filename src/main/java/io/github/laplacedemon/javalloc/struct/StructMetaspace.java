package io.github.laplacedemon.javalloc.struct;

import io.github.laplacedemon.javalloc.struct.value.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StructMetaspace {

    private Map<Class<? extends JStruct>, JStruct> classMap = new ConcurrentHashMap();

    public <T extends JStruct> T getClassObject(Class<T> structClass) {
        return getOrRegister(structClass);
    }

    public <T extends JStruct> T  getOrRegister(Class<T> jStructClass) {
        return (T)classMap.computeIfAbsent(jStructClass, c -> {
            JStruct jStruct = null;
            int offset = 0;
            try {
                jStruct = jStructClass.newInstance();

                Class clazz = jStructClass;

                while (true) {
                    Field[] declaredFields = clazz.getDeclaredFields();
                    for (int i = 0; i < declaredFields.length; i++) {
                        Field declaredField = declaredFields[i];
                        Class<?> fieldType = declaredField.getType();

                        if (fieldType.isAssignableFrom(JInt.class)) {
                            JInt jInt = (JInt) fieldType.newInstance();
                            jInt.setOffset(offset);
                            offset += JInt.SIZE;
                            declaredField.set(jStruct, jInt);
                        } else if (fieldType.isAssignableFrom(JLong.class)) {
                            JLong jLong = (JLong) fieldType.newInstance();
                            jLong.setOffset(offset);
                            offset += JLong.SIZE;
                            declaredField.set(jStruct, jLong);
                        } else if (fieldType.isAssignableFrom(JDouble.class)) {
                            JDouble jDouble = (JDouble) fieldType.newInstance();
                            jDouble.setOffset(offset);
                            offset += JLong.SIZE;
                            declaredField.set(jStruct, jDouble);
                        } else if (fieldType.isAssignableFrom(JBoolean.class)) {
                            JBoolean jBoolean = (JBoolean) fieldType.newInstance();
                            jBoolean.setOffset(offset);
                            offset += JBoolean.SIZE;
                            declaredField.set(jStruct, jBoolean);
                        } else if (fieldType.isAssignableFrom(JByte.class)) {
                            JByte jByte = (JByte) fieldType.newInstance();
                            jByte.setOffset(offset);
                            offset += JByte.SIZE;
                            declaredField.set(jStruct, jByte);
                        }
                    }

                    clazz = clazz.getSuperclass();

                    if (clazz.equals(JStruct.class) || clazz.equals(Object.class)) {
                        break;
                    }
                }


            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }

            jStruct.setSize(offset);

            return jStruct;
        });

    }
}
