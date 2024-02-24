package lab3;

import lab3.util.Pos;
import lab3.env.*;

public class Main {
    public static void main(String[] args) {

        var map = new Map("res/maze");
        var robot = new Robot(map, new Pos(4, 5));
        var flag = new Flag(map, new Pos(3, 4));

        var dfs = new DeepFirstSearch(map, robot, flag);
        dfs.search(robot.getPos());
    }
}
