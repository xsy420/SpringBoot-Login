package com.example.backend.utils;

import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.List;

public class EntityUtil {
    @SuppressWarnings("unused")
    public static <E> void setValue(List<E> entities, String key, Object value) throws IllegalAccessException {
        for (E list:entities) {
            Class<?> aClass = list.getClass();
            Field[] aFields = aClass.getDeclaredFields();//获取实体类的所有属性
            for (Field f:aFields) {
                if (f.getName().equals(key)){
                    f.setAccessible(true);//设置访问权限
                    f.set(list,value);//赋值
                }
            }
        }
    }

    public static <E> E UpdateAllNotEmpty(E oldEntity, E newEntity) {
        for (Field f: newEntity.getClass().getDeclaredFields()
             ) {
            try {
                f.setAccessible(true);
                Object newItem = f.get(newEntity);
                if (!ObjectUtils.isEmpty(newItem)) f.set(oldEntity, newItem);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return oldEntity;
    }

}
