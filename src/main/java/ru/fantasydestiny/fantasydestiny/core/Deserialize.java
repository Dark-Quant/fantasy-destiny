package ru.fantasydestiny.fantasydestiny.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Deserialize extends SerOrDeSer {

    private final String path;


    public Deserialize(Object object , String path){
        super(object);
        this.path = path;
    }

    public Object read() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        try  {
            File file = new File(path);
            forJson = mapper.readValue(file, new TypeReference<>(){});
        }
        catch (IOException e){
            System.out.print(e.getMessage());
        }

        for (Field field : fields) {
            ///////
            if(!field.isAnnotationPresent(NotSerialize.class)){
                /////////
                FieldIt fieldIs = FieldItIs(field);
                if (fieldIs == FieldIt.isPrimitive) {
                    field.set(object, forJson.get(field.getName()));
                } else if (field.getType().isEnum()) {
                    field.set(object, Enum.valueOf((Class<Enum>) field.getType(), (String) forJson.get(field.getName())));
                } else if (fieldIs == FieldIt.isList) {
                    List<Object> list = new ArrayList();
                    List<String> paths = (ArrayList<String>) forJson.get(field.getName());
                    Type parameter = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                    for (String path : paths) {
                        list.add(new Deserialize(Class.forName(parameter.getTypeName()).getConstructor().newInstance(), path).read());
                    }
                    field.set(object, list);
                }
                else {
                    field.set(new Deserialize(field.getType(),(String) forJson.get(field.getName())).read(),field.getType());
                }
            }

        }
        return this.getObject();
    }
}
