package com.shpp.p2p.cs.kturevich.assignment17.assignment15;

public class Node {
    private Node parent;
    private Node left;
    private Node right;
    private Byte value;

    Node(Byte value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    public void setLeft(Node leftChild) {
        this.left = leftChild;
    }

    public void setRight(Node rightChild) {
        this.right = rightChild;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    @Override
    public String toString() {
        Byte parentValue = null;
        Byte leftValue = null;
        Byte rightValue = null;

        if (parent != null)
            parentValue = parent.getValue();

        if (left != null)
            leftValue = left.getValue();

        if (right != null)
            rightValue = right.getValue();


        return "Node{" +
                "parentValue=" + parentValue +
                ", leftValue=" + leftValue +
                ", rightValue=" + rightValue +
                ", value='" + value + '\'' +
                '}';
    }
}
