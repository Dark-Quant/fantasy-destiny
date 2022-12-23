package ru.fantasydestiny.fantasydestiny.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.Map;

public class SerOrDeSer {

    private static Boolean isPrimitiveOrWrapper(Field field) {
        return field.getType().isPrimitive() ||
                field.getType().getSimpleName().equals("String") ||
                field.getType().getSimpleName().equals("Enum");
    }
    static Boolean isPrimitiveOrWrapper(Object obj){
        return  obj.getClass().isPrimitive();
    }

    private static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }
    private static final Map<Class<?>, Class<?>> WRAPPER_TYPE_MAP;
    static {
        WRAPPER_TYPE_MAP = new HashMap<Class<?>, Class<?>>(16);
        WRAPPER_TYPE_MAP.put(Integer.class, int.class);
        WRAPPER_TYPE_MAP.put(Byte.class, byte.class);
        WRAPPER_TYPE_MAP.put(Character.class, char.class);
        WRAPPER_TYPE_MAP.put(Boolean.class, boolean.class);
        WRAPPER_TYPE_MAP.put(Double.class, double.class);
        WRAPPER_TYPE_MAP.put(Float.class, float.class);
        WRAPPER_TYPE_MAP.put(Long.class, long.class);
        WRAPPER_TYPE_MAP.put(Short.class, short.class);
        WRAPPER_TYPE_MAP.put(Void.class, void.class);
    }

    public String Serialize(Object obj,String folder) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {

        Field[] fields = obj.getClass().getDeclaredFields();

        String name=(String)obj.getClass().getDeclaredField("title").get(obj);

        List<Field> allFields = List.of(fields);

        Map<String, Object> forJson = new HashMap<>();

        for (Field field : allFields) {
            if (field.isAnnotationPresent(Const.class)) {
                if (isPrimitiveOrWrapper(field)) {
                    forJson.put(field.getName(), field.get(obj));
                }
                else if(field.getType().getSimpleName().equals("List")){
                    List<?> list=convertObjectToList(field.get(obj));
                    List<Object> Names=new ArrayList<>();
                    for(int i = 0; i<list.size(); i++) {
                        if(WRAPPER_TYPE_MAP.containsKey(list.get(i).getClass())){
                            Names.add(list.get(i));
                        }
                        else {
                            Names.add(Serialize(list.get(i), folder + name + '/'));
                        }
                    }
                }
            }
        }
        return name;
    }
}
