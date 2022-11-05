package ru.fantasydestiny.fantasydestiny.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

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
}
