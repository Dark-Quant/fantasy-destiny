package ru.fantasydestiny.fantasydestiny.entity;

import ru.fantasydestiny.fantasydestiny.world.Location;

public class LivingEntity extends Entity {
    protected static int MAX_HEALTH;
    protected static int MAX_MANA;
    protected Location coordinate;

    public LivingEntity(Location location) {
        coordinate = location;
    }

    public Location move(Location location) {
        Location previousLocation = this.coordinate;
        this.coordinate = location;
        return previousLocation;
    }
}
