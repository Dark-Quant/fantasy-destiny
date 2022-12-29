package ru.fantasydestiny.fantasydestiny.test;

import ru.fantasydestiny.fantasydestiny.core.Deserialize;
import ru.fantasydestiny.fantasydestiny.core.Serialize;
import ru.fantasydestiny.fantasydestiny.world.Location;
import ru.fantasydestiny.fantasydestiny.world.World;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TestWorld {

    String in="World/World.json";

    String back;

    Object ItIs;
    private void open(String path) throws IOException {

        System.out.println(path+':');
        FileReader fileReader = new FileReader(path);

        char[] buf = new char[256];
        int c;
        while ((c = fileReader.read(buf)) > 0) {

            if (c < 256) {
                buf = Arrays.copyOf(buf, c);
            }
            System.out.print(buf);
        }
        System.out.print('\n');
    }
    public TestWorld() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("open - open the json\n");

            ItIs= new Deserialize(new World("",""),in).read();

            while (true) {

                open(in);

                in = scanner.nextLine();

                if(Objects.equals(in, "open")) {

                    System.out.print("Open how?\n1-World\n2-Location\n3-Entiti\n4-");

                    System.out.print("Open for path: ");

                    in = scanner.nextLine();

                    ItIs=new Deserialize(new Location("",""),in).read();

                } else if (Objects.equals(in, "cls")) {
                    return;
                } else {
                    System.out.println("Пожалуйста введите корректный запрос!!!");
                }
            }
        }
        catch (IOException e){
            System.out.print(e.getMessage());
        }
    }
}
