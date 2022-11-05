package ru.fantasydestiny.fantasydestiny.world;

import ru.fantasydestiny.fantasydestiny.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Location {
    protected final String title;
    protected final String description;
    protected List<Entity> entities = new ArrayList<>();
    protected final List<Location> neighboursLocation = new ArrayList<>();
    protected final Tiers difficulty;
    protected int limit;

    public Location(String title) {
        this.title = title;
        this.description = "";
        this.difficulty = Tiers.PEACEFUL;
    }

    public Location(String title, String description) {
        this.title = title;
        this.description = description;
        this.difficulty = Tiers.PEACEFUL;
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

    public String getTitle() {
        return title;
    }

    public List<Location> getNeighboursLocation() {
        return neighboursLocation;
    }
}
