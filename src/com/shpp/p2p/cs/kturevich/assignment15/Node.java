package com.shpp.p2p.cs.kturevich.assignment15;

public class Node<T> {
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;
    private T value;

    Node(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setLeft(Node<T> leftChild) {
        this.left = leftChild;
    }

    public void setRight(Node<T> rightChild) {
        this.right = rightChild;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        T parentValue = (T) "none";
        T leftValue = (T) "none";
        T rightValue = (T) "none";

        if (parent != null)
            parentValue = parent.getValue();

        if (left != null)
            leftValue = left.getValue();

        if (right != null)
            rightValue = right.getValue();


        return "Node{" +
                "parent=" + parentValue +
                ", left=" + leftValue +
                ", right=" + rightValue +
                ", value='" + value + '\'' +
                '}';
    }
}
