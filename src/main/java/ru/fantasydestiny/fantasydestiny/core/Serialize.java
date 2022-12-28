package ru.fantasydestiny.fantasydestiny.core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Serialize extends SerOrDeSer {


    private final String path;

    protected String name;

    public Serialize(Object object, String path) {

        super(object);

        try{
            this.name=(String)object.getClass().getField("title").get(object);
        }
        catch(NoSuchFieldException | IllegalAccessException e){
            System.out.print(e.getMessage());
        }

        if(path.isEmpty()){
            this.path=name;
        }
        else{
            this.path=path+'/'+name;
        }
    }

    public String getName(){
        return name;
    }

    public String write() throws IllegalAccessException{

        for (Field field : fields) {
            ///////
            if(!field.isAnnotationPresent(NotSerialize.class)){
                /////////
                FieldIt fieldIs = FieldItIs(field);
                if (fieldIs == FieldIt.isPrimitive || fieldIs == FieldIt.isEnum) {
                    forJson.put(field.getName(), field.get(object));
                } else if (fieldIs == FieldIt.isList) {
                    List<?> list = convertObjectToList(field.get(object));
                    List<Object> Names = new ArrayList<>();
                    for (Object o : list) {
                        if (WRAPPER_TYPE_MAP.containsKey(o.getClass()) || o.getClass().isEnum()) {
                            Names.add(o);
                        } else {
                            Serialize serialize= new Serialize(o, path);
                            Names.add(serialize.write()+'/'+serialize.getName()+".json");
                        }
                    }
                    forJson.put(field.getName(), Names);
                }
                else {
                    Serialize serialize=new Serialize(field.get(object), path);
                    forJson.put(field.getName(), serialize.write()+'/'+serialize.getName()+".json");
                }
            }
        }
        File theDir = new File(path);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        try(FileWriter json = new FileWriter(path+'/'+name+".json",false)){
            json.write(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(forJson));
            json.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return path;
    }

}
