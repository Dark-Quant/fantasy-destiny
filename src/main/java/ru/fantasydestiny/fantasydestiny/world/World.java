package ru.fantasydestiny.fantasydestiny.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.io.*;

public class World {
    private final List<Location> locations = new ArrayList<>();
    private static World instance = new World();

    private World() { }

    public static World getInstance() {
        return instance;
    }

    public List<Location> getLocations() {
        return locations;
    }
    public void addLocation(Location L){locations.add(L);}

    public void Save(String WorldName){
        try {
            FileOutputStream f = new FileOutputStream(WorldName);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(this);
            f.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void Get(String WorldName){
        try {
            FileInputStream f = new FileInputStream(WorldName);
            ObjectInputStream in = new ObjectInputStream(f);
            World w=(World) in.readObject();
            this=w;
            f.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
