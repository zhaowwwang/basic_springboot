package com.basic.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 实现对象之间属性值得拷贝
 * @author: wangzw
 * @version: 1.0
 * @date: 2017/12/18 10:42
 */
public class ObjectUtils {

    /**
     * 两个实体之间相同属性值的拷贝
     * @author: wangzw
     * @version: 1.0
     * @date: 2017/12/18 10:35
     */
    public static void copyProperty(Object class1,Object class2) throws Exception{
        Class clazz1 = Class.forName(class1.getClass().getName());
        Class clazz2 = Class.forName(class2.getClass().getName());
        Field[] fields1 = clazz1.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();
        for (Field f1 : fields1) {
            if(f1.getName().equals("id")){
                continue;
            }
            Object value = invokeGetMethod(class1 ,f1.getName());
            if (value == null || value ==""){
                continue;
            }
            for (Field f2 : fields2) {
                if(f1.getName().equals(f2.getName())){
                    Object[] obj = new Object[1];
                    obj[0] = value;
                    invokeSetMethod(class2, f2.getName(), obj);
                }
            }
        }
    }

    /**
     * 执行属性的get方法
     * @author: wangzw
     * @version: 1.0
     * @date: 2017/12/18 10:38
     */
    private static Object invokeGetMethod(Object clazz, String fieldName){
        String methodName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
        Method method = null;
        try{
            method = Class.forName(clazz.getClass().getName()).getDeclaredMethod("get" + methodName);
            return method.invoke(clazz);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 执行属性的set方法
     * @author: wangzw
     * @version: 1.0
     * @date: 2017/12/18 10:38
     */
    private static void invokeSetMethod(Object clazz, String fieldName, Object[] args){
        String methodName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
        Method method = null;
        try{
            Class[] parameterTypes = new Class[1];
            Class c = Class.forName(clazz.getClass().getName());
            Field field = c.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            method = c.getDeclaredMethod("set" + methodName,parameterTypes);
            if(method != null){
                method.invoke(clazz,args);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
