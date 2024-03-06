package lab3;

import lab3.util.Pos;
import lab3.env.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);

        var map = new Map("res/maze");
        System.out.println("Map[" + map.getWidth() + "," + map.getHeight() + "]:\n");
        map.print();
        System.out.println();

        System.out.print("Enter start robot's coordinates: ");
        int robotX = scan.nextInt();
        int robotY = scan.nextInt();

        if (!map.isInsideMap(new Pos(robotX, robotY))) {
            throw new RuntimeException("The robot went off the map");
        }
        var robot = new Robot(map, new Pos(robotX, robotY));

        System.out.print("Enter flag's coordinates: ");
        int flagX = scan.nextInt();
        int flagY = scan.nextInt();

        if (!map.isInsideMap(new Pos(flagX, flagY))) {
            throw new RuntimeException("The flag is off the map");
        }
        var flag = new Flag(map, new Pos(flagX, flagY));

        scan.close();

        var automaton = new Automaton(map, robot, flag);
        automaton.execute();
    }
}
