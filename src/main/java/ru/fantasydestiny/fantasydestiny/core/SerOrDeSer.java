package ru.fantasydestiny.fantasydestiny.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.Map;

public class SerOrDeSer {

    static List<Field> getFieldNames(Field[] fields) {
        return List.of(fields);
    }
    static  Boolean isPrimitiveOrWrapper(Field field){
        if(field.getType().isPrimitive() ||
                field.getType().getSimpleName().equals("String")||
                field.getType().getSimpleName().equals("Integer")||
                field.getType().getSimpleName().equals("Boolean")||
                field.getType().getSimpleName().equals("Enum")||
                field.getType().getSimpleName().equals("Double")||
                field.getType().getSimpleName().equals("Float")||
                field.getType().getSimpleName().equals("Long"))
        {
            return true;
        }
        return false;
    }

    public void Serialize(Object obj) throws NoSuchMethodException {

        Field[] fields=obj.getClass().getFields();

        List<Field> AllFields=getFieldNames(fields);

        Map<String, Object> ForJson = new HashMap<>();

        for(Field field : AllFields){

            if(field.isAnnotationPresent(Const.class)){
                if(isPrimitiveOrWrapper(field)){
                    ForJson.put(field.getName(),field.get(obj));
                }
                else if(field.getType().isEnum()){
                    ForJson.put(field.getName(),field.getType().getSimpleName());
                }
                else {
                    Method get=field.getType().getMethod("get");
                    Method size=field.getType().getMethod("size");
                    int Size=size.invoke(obj);
                    for(int i=0;i< Size;i++){

                    }
                }
            }

        }
    }
}
