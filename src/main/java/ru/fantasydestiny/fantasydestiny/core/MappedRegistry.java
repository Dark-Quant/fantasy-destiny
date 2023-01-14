package ru.fantasydestiny.fantasydestiny.core;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MappedRegistry {

    public Map<Class<?>, List<Object>> PrimitiveDates=new HashMap<>();

    public Map<Class<?>,List<byte[]>> OtherDates=new HashMap<>();

    MappedRegistry(){

        this.PrimitiveDates.put(Integer.class, new ArrayList<>());

        this.PrimitiveDates.put(Character.class, new ArrayList<>());

        this.PrimitiveDates.put(Boolean.class, new ArrayList<>());

        this.PrimitiveDates.put(Byte.class, new ArrayList<>());

        this.PrimitiveDates.put(Float.class,new ArrayList<>());

        this.PrimitiveDates.put(Long.class,new ArrayList<>());

        this.PrimitiveDates.put(Short.class,new ArrayList<>());

        this.PrimitiveDates.put(Void.class,new ArrayList<>());

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

    private static final Set<Class> WRAPPER_TYPES = new HashSet(Arrays.asList(
            Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class));
    public static FieldIt ObjectItIs(Class clazz) {
        if(WRAPPER_TYPES.contains(clazz)){
            return FieldIt.isPrimitive;
        }
        else if(clazz.getSimpleName().equals("List")){
            return FieldIt.isList;
        }
        else if(clazz.isEnum()){
            return FieldIt.isEnum;
        }
        else if(clazz.isArray()){
            return FieldIt.isArray;
        }
        else if(clazz.getSimpleName().equals("String")){
            return FieldIt.isString;
        }
        else{
            return FieldIt.isOtherObject;
        }
    }

    protected FieldIt FieldItIs(Field field) {
        if (field.getType().isPrimitive()) {
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

    private byte[] StringToBytes(String str){
        return str.getBytes(StandardCharsets.UTF_8);
    }

    private Byte AddPrimitive(Class<?> clazz,Object obj){

        List<Object> list=PrimitiveDates.get(clazz);

        byte size=(byte)list.size();

        for(byte i=0;i<size;i++){
            if(obj.equals(list.get(i))){
                return i;
            }
        }
        list.add(obj);
        return size;
    }
    private Byte AddEnum(Class<?> clazz,Object obj){

        String str=obj.toString();

        byte[] buff=StringToBytes(str);

        List<byte[]> list=new ArrayList<>();

        if(!OtherDates.containsKey(clazz)){
            OtherDates.put(clazz,list);
        }
        else{
            list=OtherDates.get(clazz);
        }

        byte size=(byte)list.size();

        for(byte b:buff){
            b=AddPrimitive(Byte.class,(Byte)b);
        }

        for(byte i=0;i<size;i++){
            if(Arrays.equals(list.get(i),buff )){
                return i;
            }
        }
        list.add(buff);
        return size;
    }

    private Byte AddOtherObject(Class<?> clazz,Object obj) throws IllegalAccessException {

        List<Field> fields=List.of(obj.getClass().getDeclaredFields());

        List<byte[]> list=new ArrayList<>();

        if(!OtherDates.containsKey(clazz)){
            OtherDates.put(clazz,list);
        }
        else{
            list=OtherDates.get(clazz);
        }

        byte size=(byte)fields.size();

        byte[] buff=new byte[size];

        for (byte i=0;i<size;i++) {

            buff[i]=RegistryObject(fields.get(i).get(obj));

        }
        for(byte i=0;i<size;i++){
            if(Arrays.equals(list.get(i),buff )){
                return i;
            }
        }
        list.add(buff);
        return size;
    }

    private Byte AddList(Class<?> clazz,Object obj) throws IllegalAccessException {

        List<?> list=convertObjectToList(obj);

        List<byte[]> l=new ArrayList<>();

        if(!OtherDates.containsKey(clazz)){
            OtherDates.put(clazz,l);
        }
        else{
            l=OtherDates.get(clazz);
        }

        byte size=(byte)list.size();

        byte[] buff=new byte[size];

        for (byte i=0;i<size;i++) {

            buff[i]=RegistryObject(list.get(i));

        }

        for(byte i=0;i<size;i++){
            if(Arrays.equals(l.get(i),buff )){
                return i;
            }
        }
        l.add(buff);
        return size;

    }

    public Byte RegistryObject(Object obj) throws IllegalAccessException {

        Class<?> clazz=obj.getClass();

        FieldIt fieldIt=ObjectItIs(clazz);

        switch (fieldIt){

            case isPrimitive -> {
                return AddPrimitive(clazz,obj);
            }
            case isEnum, isString -> {
                return AddEnum(clazz,obj);
            }
            case isList -> {

            }
        }

        return AddOtherObject(clazz,obj);
    }
}
