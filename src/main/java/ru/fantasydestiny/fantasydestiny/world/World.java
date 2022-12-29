package ru.fantasydestiny.fantasydestiny.world;

import ru.fantasydestiny.fantasydestiny.core.NotSerialize;

import java.util.ArrayList;
import java.util.List;

public class World{

    public String title;

    public String describtion;

    public List<Location> locations = new ArrayList<>();

    public String contains;

    @NotSerialize
    public static World instance = new World();

    public World(String title,String describtion) {

        this.title=title;

        this.describtion=describtion;

    }
    private World() {

        this.title="title";

        this.describtion="describtion";

    }

    public String getTitle(){
        return this.title;
    }

    public String getDescribtion(){
        return this.describtion;
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
