package lab3;

import lab3.env.Flag;
import lab3.env.Robot;
import lab3.token.Token;

import java.io.*;
import java.util.*;

import lab3.node.*;

public class Automaton {
    public static void execute(Robot robot, lab3.env.Map map, Flag flag) throws IOException {
        var fr = new FileReader("res/example_input_file.txt");
        var parser = new Parser(fr);

        var token = parser.nextToken();
        var list = new ArrayList<Token>();

        while (token != null) {
            list.add(token);
            token = parser.nextToken();
        }

        parser.close();

        var syntaxAnalyzer = new SyntaxAnalyzer();
        var root = syntaxAnalyzer.createNodes(list);
        Node currentNode = root;
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (currentNode instanceof StartNode sn) {
                System.out.println("B");
                currentNode = sn.getNextNode();
            } else if (currentNode instanceof LabelNode ln) {
                currentNode = ln.getNextNode();
            } else if (currentNode instanceof CommandNode cn) {
                System.out.println("Y" + cn.getIndex());
                currentNode = cn.getNextNode();
            } else if (currentNode instanceof ConditionNode cn) {
                System.out.print("X" + cn.getIndex() + ": ");
                int x = sc.nextInt();
                currentNode = x == 0 ? cn.getFalseNode() : cn.getTrueNode();
            } else if (currentNode instanceof EndNode) {
                System.out.println("E");
                break;
            }
        }
    }
}

