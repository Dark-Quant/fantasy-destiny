package ru.fantasydestiny.fantasydestiny.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Deserialize extends SerOrDeSer {

    private final String path;

    public Deserialize(Class<?> object , String path){

        super(object);

        this.path = path;
        this.fields = List.of(object.getDeclaredFields()); // -Ещё раз скомпилируй. Ответь в ds.
    }


    public Object read() throws IllegalAccessException {
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
                if (fieldIs == FieldIt.isPrimitive || fieldIs == FieldIt.isEnum) {
                    field.set(forJson.get(field.getName()),field.getType());
                } else if (fieldIs == FieldIt.isList) {
                    List<Object> list = new ArrayList();
                    String[] paths = (String[]) (forJson.get(field.getName()));
                    for (String path : paths) {
                        list.add(new Deserialize(field.getType(),(String) forJson.get(field.getName())).read());
                    }
                    field.set(list, field.getType());
                }
                else {
                    field.set(new Deserialize(field.getType(),(String) forJson.get(field.getName())).read(),field.getType());
                }
            }
        }
        return this.getObject();
    }
}
