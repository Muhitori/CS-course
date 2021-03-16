package com.shpp.p2p.cs.kturevich.assignment16;

public class Node<T> {
    private Node<T> prev;
    private Node<T> next;
    private T value;

    Node(T value) {
        this.value = value;
    }

    Node(Node<T> prev, T value, Node<T> next) {
        this.prev = prev;
        this.value = value;
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean hasPrev () {
        return this.prev != null;
    }

    public boolean hasNext () {
        return this.next != null;
    }

    public boolean hasValue () {
        return this.value != null;
    }
}
