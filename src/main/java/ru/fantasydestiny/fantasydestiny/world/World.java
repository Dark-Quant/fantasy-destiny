package ru.fantasydestiny.fantasydestiny.world;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class World{

    public class json{

        private final String name;

        private final String describtion;

        protected json(String name,String describtion){

            this.name=name;

            this.describtion=describtion;

        }
    }
    private final List<Location> locations = new ArrayList<>();
    private static World instance = new World("World.data");

    public World(String path) {

        File f=new File(path);



    }

    public static World getInstance() {
        return instance;
    }

    public List<Location> getLocations() {
        return locations;
    }
    public void addLocation(Location L){locations.add(L);}

}
/*
public class World extends world{

    public World(String path) throws IOException {

        FileReader reader = new FileReader(path);

        StringBuilder builder = new StringBuilder();
        int charsRead = -1;
        char[] chars = new char[100];
        do{
            charsRead = reader.read(chars,0,chars.length);

            if(charsRead>0)
                builder.append(chars,0,charsRead);
        }while(charsRead>0);

        String json = builder.toString();

        ObjectMapper objectMapper = new ObjectMapper();

        world w=objectMapper.readValue(json, world.class);

    }

}*/
