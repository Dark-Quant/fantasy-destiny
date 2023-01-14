package ru.fantasydestiny.fantasydestiny;

import ru.fantasydestiny.fantasydestiny.core.Deserialize;
import ru.fantasydestiny.fantasydestiny.core.Serialize;
import ru.fantasydestiny.fantasydestiny.test.TestWorld;
import ru.fantasydestiny.fantasydestiny.world.Location;
import ru.fantasydestiny.fantasydestiny.world.World;

public class Client {
    public static void main(String[] args) throws Exception {
        //new TestWorld();
        World world = new World("dfghdfh","wre");
        world.addLocation(new Location("sfsdf","sdfas"));
        new Serialize(world,"").write();
        System.out.println("");
//        world.getLocations().add(new Location("My Location"));
//        world.getLocations().add(new Location("Quantum Spidery Cave"));
//        world.getLocations().add(new Location("Great Empire"));
//        world.getLocations().add(new Location("Quantum Empire"));
//        world.getLocations().add(new Location("Lapis Lazuli Spider's Cave "));
//        world.getLocations().add(new Location("Forest"));
//        world.getLocations().add(new Location("Oak Forest"));
//        world.getLocations().add(new Location("Great Motion"));
//        world.getLocations().add(new Location("Death River"));
//        world.getLocations().add(new Location("Sea of Life"));
//
//        world.getLocations().get(0).getNeighboursLocation().add(world.getLocations().get(1));
//        world.getLocations().get(1).getNeighboursLocation().add(world.getLocations().get(2));
//        world.getLocations().get(2).getNeighboursLocation().add(world.getLocations().get(3));
//        world.getLocations().get(3).getNeighboursLocation().add(world.getLocations().get(4));
//        world.getLocations().get(4).getNeighboursLocation().add(world.getLocations().get(5));
//        world.getLocations().get(5).getNeighboursLocation().add(world.getLocations().get(6));
//        world.getLocations().get(6).getNeighboursLocation().add(world.getLocations().get(7));
//        world.getLocations().get(7).getNeighboursLocation().add(world.getLocations().get(8));
//        world.getLocations().get(8).getNeighboursLocation().add(world.getLocations().get(9));
//        world.getLocations().get(9).getNeighboursLocation().add(world.getLocations().get(0));
//        for (Location location : world.getLocations()) {
//            System.out.println(location.getTitle());
//            System.out.println("Соседи: ");
//            for (Location neigh : location.getNeighboursLocation()) {
//                System.out.println(neigh.getTitle());
//            }
//            System.out.println();
//        }
    }
}