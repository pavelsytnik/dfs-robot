package lab3;

import lab3.util.Pos;
import lab3.env.Map;
import lab3.env.Robot;

public class Main {
    public static void main(String[] args) {
        var map = new Map("res/map");
        var robot = new Robot(map, new Pos(2, 1));
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                var p = new Pos(x, y);
                if (map.isWall(p)) {
                    System.out.print("# ");
                } else if (robot.getPos().equals(p)) {
                    System.out.print("@ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
