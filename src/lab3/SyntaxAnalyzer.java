package lab3;

import lab3.node.*;
import lab3.token.Token;

import java.util.ArrayList;
import java.util.List;

public class SyntaxAnalyzer {

    public StartNode createNodes(List<? extends Token> tokens) {
        if (tokens.isEmpty())
            throw new RuntimeException("Syntax Error: Empty input");

        if (!tokens.get(0).getType().equals("B"))
            throw new RuntimeException("Syntax Error: 'B' as begin marker is missing");

        if (!tokens.get(tokens.size() - 1).getType().equals("E"))
            throw new RuntimeException("Syntax Error: 'E' as end marker is missing");


        /*
        First we should iterate over the tokens to receive all the labels
         */
        var labels = new ArrayList<LabelNode>();

        for (int i = 1; i < tokens.size() - 1; i++) {
            var currentToken = tokens.get(i);
            if (currentToken.getType().equals(">")) {
                labels.add(new LabelNode(Integer.parseInt(currentToken.getIndex())));
            }
        }

        /*
        root keeps the root of our graph. We need to get child nodes for traversal.
        Finally, the root is returned
         */
        var root = new StartNode();
        Node currentNode = root;

        for (int i = 1; i < tokens.size(); i++) {
            var currentToken = tokens.get(i);
            Node nextNode;
            switch (currentToken.getType()) {
                case ">", "#", "W" -> nextNode = findLabel(labels, currentToken.getIndex());
                case "X" -> nextNode = new ConditionNode(currentToken.getIndex().charAt(0));
                case "Y" -> nextNode = new CommandNode(currentToken.getIndex().charAt(0));
                case "E" -> nextNode = new EndNode();
                default -> throw new RuntimeException("Syntax Error: Unknown token");
            }
            if (currentNode != null) {
                if (currentNode instanceof NodeWithOnlyChild n) {
                    if (currentToken.getType().equals("#"))
                        throw new RuntimeException("Syntax Error: '#' is forbidden here");
                    n.setNextNode(nextNode);
                } else if (currentNode instanceof ConditionNode cn) {
                    if (cn.getFalseNode() == null) {
                        if (!currentToken.getType().equals("#"))
                            throw new RuntimeException("Syntax Error: '#' was expected");
                        cn.setFalseNode((LabelNode) nextNode);
                    } else {
                        if (currentToken.getType().equals("#"))
                            throw new RuntimeException("Syntax Error: '#' is forbidden here");
                        cn.setTrueNode(nextNode);
                    }
                }
            }
            if (currentToken.getType().equals("W")) currentNode = null;
            else if (!currentToken.getType().equals("#")) currentNode = nextNode;
        }
        return root;
    }

    private LabelNode findLabel(List<LabelNode> labels, String index) {
        for (var label : labels) {
            if (label.getIndex() == Integer.parseInt(index)) {
                return label;
            }
        }
        return null;
    }
}
