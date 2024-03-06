package lab3;

import lab3.util.Pos;
import lab3.env.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        var map = new Map("res/maze");
        var robot = new Robot(map, new Pos(4, 5));
        var flag = new Flag(map, new Pos(1, 1));

        Automaton.execute(robot, map, flag);

        var dfs = new DeepFirstSearch(map, robot, flag);
        dfs.search(robot.getPos());
    }
}
