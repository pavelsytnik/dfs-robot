package lab3.node;

public interface NodeWithOnlyChild extends Node {
    Node getNextNode();
    void setNextNode(Node nextNode);
}
