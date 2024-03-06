package lab3;

import lab3.env.Flag;
import lab3.env.Robot;
import lab3.token.Token;

import java.io.*;
import java.util.*;

import lab3.node.*;
import lab3.util.Pos;

public class Automaton {

    private Robot robot;
    private lab3.env.Map map;
    private Flag flag;

    private final Set<Pos> visited = new HashSet<>();
    private final Stack<Pos> stack = new Stack<>();

    public Automaton(lab3.env.Map map, Robot robot, Flag flag){
        this.map = map;
        this.flag = flag;
        this.robot = robot;
        visited.add(robot.getPos());
    }

    private boolean isVisitedOrWall(char dir) {
        switch (dir) {
            case 'l':
                return forbidden(robot.getPos().left());
            case 'r':
                return forbidden(robot.getPos().right());
            case 'd':
                return forbidden(robot.getPos().down());
            case 'u':
                return forbidden(robot.getPos().up());
            default:
                throw new RuntimeException("There's no such direction like " + dir);
        }
    }

    private boolean forbidden(Pos pos) {
        return !map.isInsideMap(pos) || visited.contains(pos) || map.isWall(pos);
    }

    private void move(char dir) {
        switch (dir) {
            case 'l':
                updateRobotPos(robot.getPos().left());
                break;
            case 'r':
                updateRobotPos(robot.getPos().right());
                break;
            case 'd':
                updateRobotPos(robot.getPos().down());
                break;
            case 'u':
                updateRobotPos(robot.getPos().up());
                break;
            default:
                throw new RuntimeException("There's no such direction like " + dir);
        }
    }

    private void updateRobotPos(Pos pos) {
        stack.push(robot.getPos());
        visited.add(pos);
        robot.setPos(pos);
    }

    private void moveRobotBack() {
        Pos pos = stack.pop();
        robot.setPos(pos);
    }

    public void execute() throws IOException {

        var fr = new FileReader("res/path_finder.txt");
        var parser = new Parser(fr);

        var token = parser.nextToken();
        var list = new ArrayList<Token>();

        while (token != null) {
            list.add(token);
            token = parser.nextToken();
        }

        parser.close();

        var syntaxAnalyzer = new SyntaxAnalyzer();
        Node currentNode = syntaxAnalyzer.createNodes(list);

        delay(300);
        System.out.println("\n");
        printMap();

        boolean flagFound = false;

        while (true) {
            if (currentNode instanceof StartNode sn) {
                currentNode = sn.getNextNode();
            } else if (currentNode instanceof LabelNode ln) {
                currentNode = ln.getNextNode();
            } else if (currentNode instanceof CommandNode cn) {
                if (cn.getIndex() == 'e') {
                    if (!stack.isEmpty()) {
                        moveRobotBack();
                    } else {
                        break;
                    }
                } else {
                    move(cn.getIndex());
                    if (robot.getPos().equals(flag.getPos())) {
                        flagFound = true;
                    }
                }
                currentNode = cn.getNextNode();

                delay(300);
                System.out.println("\n");
                printMap();
            } else if (currentNode instanceof ConditionNode cn) {
                if (cn.getIndex() == 'e') {
                    currentNode = flagFound ? cn.getTrueNode() : cn.getFalseNode();
                } else {
                    currentNode = isVisitedOrWall(cn.getIndex()) ? cn.getTrueNode() : cn.getFalseNode();
                }
            } else if (currentNode instanceof EndNode) {
                break;
            }
        }
        System.out.println("\nEnd of loop");
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
                    System.out.print("% ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    private void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

