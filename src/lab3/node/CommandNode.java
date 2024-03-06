package lab3.node;

public class CommandNode implements NodeWithOnlyChild {

    private Node nextNode;
    private final char index;

    public CommandNode(char index) {
        this.index = index;
    }

    @Override
    public Node getNextNode() {
        return nextNode;
    }

    @Override
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public char getIndex() {
        return index;
    }
}
