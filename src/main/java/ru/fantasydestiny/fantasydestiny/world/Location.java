package ru.fantasydestiny.fantasydestiny.world;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.fantasydestiny.fantasydestiny.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Location {
    public final String title;

    public final String description;
    public final Tiers difficulty;


    public List<Entity> entities = new ArrayList<>();
    public final List<Location> neighboursLocation = new ArrayList<>();

    public int limit;

    public Location(String title, String description) {
        this.title = title;
        this.description = description;
        this.difficulty = Tiers.PEACEFUL;
        this.limit=100;
    }

    public Location(String title, List<Location> neighboursLocation) {
        this(title, "", Tiers.PEACEFUL, neighboursLocation);
    }

    public Location(String title, Tiers difficulty) {
        this(title, "", difficulty, new ArrayList<>(2));
    }

    public Location(String title, String description, Tiers difficulty, List<Location> neighboursLocation) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.neighboursLocation.addAll(neighboursLocation);
    }

    public Location TakeLocationForJSON(String path) throws IOException {

        File f=new File(path);

        ObjectMapper objectMapper=new ObjectMapper();

        return objectMapper.readValue(f,Location.class);

    }

    public String getTitle() {
        return title;
    }

    public List<Location> getNeighboursLocation() {
        return neighboursLocation;
    }
}
