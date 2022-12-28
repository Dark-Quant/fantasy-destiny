package ru.fantasydestiny.fantasydestiny.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.Map;

public class SerOrDeSer {

    final protected Object object;

    protected List<Field> fields;
    
    protected Map<String,Object> forJson=new HashMap<>();

    protected SerOrDeSer(Object object) {

        this.object=object;

        this.fields= List.of(object.getClass().getDeclaredFields());

    }
    public Object getObject(){
        return object;
    }
    public List<Field> getFields(){
        return fields;
    }

    private static Boolean isPrimitiveOrWrapper(Field field) {
        return field.getType().isPrimitive() ||
                field.getType().getSimpleName().equals("String");
    }
    static Boolean isPrimitiveOrWrapper(Object obj){
        return  obj.getClass().isPrimitive();
    }

    protected static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }
    protected static final Map<Class<?>, Class<?>> WRAPPER_TYPE_MAP;
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
    protected FieldIt FieldItIs(Field field) {
        if (isPrimitiveOrWrapper(field)) {
            return FieldIt.isPrimitive;
        }
        else if(field.getType().getSimpleName().equals("List")){
            return FieldIt.isList;
        } else if (field.getType().isEnum()) {
            return FieldIt.isEnum;
        } else{
            return FieldIt.isOtherObject;
        }
    }
}
