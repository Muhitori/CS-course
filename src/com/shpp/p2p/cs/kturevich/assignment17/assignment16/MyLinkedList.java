package com.shpp.p2p.cs.kturevich.assignment17.assignment16;

import java.util.Iterator;

//LinkedList implementation
@SuppressWarnings("unchecked")
public class MyLinkedList<T> implements Iterable<T> {
    int size = 0;
    Node<T> first;
    Node<T> last;

    //Methods to add
    public void add(T obj) {
        addLast(obj);
    }

    public void add(int index, T obj) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index is out of bounds!");

        if (index == 0) {
            addFirst(obj);
            return;
        }

        if (index == size) {
            addLast(obj);
            return;
        }

        Node<T> temp = this.first;
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                break;
            }
            temp = temp.getNext();
        }

        Node<T> newNode = new Node<>(temp.getPrev(), obj, temp);
        temp.getPrev().setNext(newNode);
        temp.setPrev(newNode);

        this.size++;
    }

    public void addFirst(T obj) {
        Node<T> newNode = new Node<>(obj);

        if (size == 0) {
            this.first = this.last = newNode;
            this.size++;
            return;
        }

        Node<T> temp = this.first;
        this.first = newNode;

        newNode.setNext(temp);
        temp.setPrev(newNode);

        this.size++;
    }

    public void addLast(T obj) {
        Node<T> newNode = new Node<>(obj);

        if (size == 0) {
            this.first = this.last = newNode;
            this.size++;
            return;
        }

        Node<T> temp = this.last;
        this.last = newNode;

        newNode.setPrev(temp);
        temp.setNext(newNode);
        this.size++;
    }

    //Methods to get
    public T get(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index is out of bounds!");

        Node<T> current = this.first;

        for (int i = 0; i < this.size; i++) {
            if (i == index)
                return current.getValue();

            current = current.getNext();
        }
        return null;
    }

    public T getFirst() {
        return this.first.getValue();
    }

    public T getLast() {
        return this.last.getValue();
    }

    //Methods to elements check
    public boolean contains(T obj) {
        if (this.size == 0)
            return false;

        Node<T> current = this.first;
        for (int i = 0; i < this.size; i++) {
            if (current.getValue() == obj)
                return true;

            current = current.getNext();
        }
        return false;
    }

    public int indexOf(T obj) {
        if (this.size == 0)
            return -1;

        Node<T> current = this.first;
        for (int i = 0; i < this.size; i++) {
            if (current.getValue() == obj)
                return i;

            current = current.getNext();
        }
        return -1;
    }

    public int lastIndexOf(T obj) {
        int result = -1;

        if (this.size == 0)
            return result;

        Node<T> current = this.first;
        for (int i = 0; i < this.size; i++) {
            if (current.getValue() == obj)
                result = i;

            current = current.getNext();
        }
        return result;
    }

    //Methods to remove
    public void remove(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index is out of bounds!");

        if (index == 0) {
            removeFirst();
            return;
        }

        if (index == size) {
            removeLast();
            return;
        }

        Node<T> temp = this.first;
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                break;
            }
            temp = temp.getNext();
        }

        Node<T> nextNode = temp.getNext();
        temp.getPrev().setNext(temp.getNext());
        nextNode.setPrev(temp.getPrev());

        this.size--;
    }

    public void removeFirst() {
        this.first = this.first.getNext();
        this.first.setPrev(null);
        this.size--;
    }

    public void removeLast() {
        this.last = this.last.getPrev();
        this.last.setNext(null);
        this.size--;
    }

    //Queue and Stack methods
    public void push(T obj) {
        addLast(obj);
    }

    public T pop() {
        T result = this.last.getValue();
        removeLast();
        return result;
    }

    public T poll() {
        T result = this.first.getValue();
        removeFirst();
        return result;
    }

    public void clear() {
        this.first = this.last = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public T[] toArray() {
        T[] result = (T[]) new Object[this.size];
        if (result.length == 0)
            return result;

        Node<T> current = this.first;
        for (int i = 0; i < this.size; i++) {
            result[i] = current.getValue();
            current = current.getNext();
        }

        return result;
    }

    //Iterator implementation
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T value = current.getValue();
                    current = current.getNext();
                    return value;
                }
                return null;
            }
        };
    }
}
