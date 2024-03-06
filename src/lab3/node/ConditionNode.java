package lab3.node;

public class ConditionNode implements Node {

    private Node trueNode;
    private LabelNode falseNode;
    private final char index;

    public ConditionNode(char index) {
        this.index = index;
    }

    public Node getTrueNode() {
        return trueNode;
    }

    public void setTrueNode(Node trueNode) {
        this.trueNode = trueNode;
    }

    public LabelNode getFalseNode() {
        return falseNode;
    }

    public void setFalseNode(LabelNode falseNode) {
        this.falseNode = falseNode;
    }

    public char getIndex() {
        return index;
    }
}
