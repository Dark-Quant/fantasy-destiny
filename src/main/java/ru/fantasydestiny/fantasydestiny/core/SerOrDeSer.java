package ru.fantasydestiny.fantasydestiny.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerOrDeSer {

    static Boolean isPrimitiveOrWrapper(Field field) {
        return field.getType().isPrimitive() ||
                field.getType().getSimpleName().equals("String") ||
                field.getType().getSimpleName().equals("Enum");
    }

    public void Serialize(Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Field[] fields = obj.getClass().getFields();

        List<Field> allFields = List.of(fields);

        Map<String, Object> forJson = new HashMap<>();

        for (Field field : allFields) {
            if (!field.isAnnotationPresent(Constant.class)) {
                continue;
            }

            if (isPrimitiveOrWrapper(field)) {
                forJson.put(field.getName(), field.get(obj));
            } else if (field.getType().isEnum()) {
                forJson.put(field.getName(), field.getType().getSimpleName());
            } else {
                List<Object> list = (ArrayList<Object>) field.get(obj);
                int Size = list.size();
                for (int i = 0; i < Size; i++) {
                }
            }

        }
    }
}
