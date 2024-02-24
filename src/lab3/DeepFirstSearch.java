package lab3;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

import lab3.env.*;
import lab3.util.Pos;

public class DeepFirstSearch {

    private final Map map;
    private final Robot robot;
    private final Flag flag;

    private final Set<Pos> visited;

    boolean flagFound;

    public DeepFirstSearch(Map map, Robot robot, Flag flag) {
        this.map = map;
        this.robot = robot;
        this.flag = flag;

        visited = new HashSet<>();

        flagFound = false;
    }

    public void search(Pos pos) {

        update(pos);

        visited.add(pos);
        List<Pos> cells = map.getAdjacentCells(pos);

//        if (cells.contains(flag.getPos())) {
//            int i = cells.indexOf(flag.getPos());
//            search(cells.get(i));
//        }

        if (robot.getPos().equals(flag.getPos())) {
            flagFound = true;
            return;
        }

        for (var c : cells) {
            if (!(visited.contains(c) || map.isWall(c))) {
                search(c);
                update(pos);
            }
        }
    }

    private void printMap() {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                var p = new Pos(x, y);
                if (map.isWall(p)) {
                    System.out.print("# ");
                } else if (robot.getPos().equals(p)) {
                    System.out.print("@ ");
                } else if (flag.getPos().equals(p)) {
                    System.out.print("$ ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    private void update(Pos pos) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        robot.setPos(pos);
        System.out.println();
        printMap();
    }
}


