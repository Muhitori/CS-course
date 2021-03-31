package com.shpp.p2p.cs.kturevich.assignment16;

import java.util.Iterator;

/**
 * LinkedList implementation
 * */
@SuppressWarnings("unchecked")
public class MyLinkedList<T> implements Iterable<T> {
    int size = 0;
    Node<T> first;
    Node<T> last;

    /**
     * Method to add
     * */
    public void add(T obj) {
        addLast(obj);
    }

    /**
     * Method to add by index
     * */
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

    /**
     * Method to add at the beginning of the list
     * */
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

    /**
     * Method to add at the end of the list
     * */
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

    /**
     * Method to get by index
     * */
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

    /**
     * Method to get first element
     * */
    public T getFirst() {
        return this.first.getValue();
    }

    /**
     * Method to get last element
     * */
    public T getLast() {
        return this.last.getValue();
    }

    /**
     * Method to check if object exist in array
     * */
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

    /**
     * Method to check index of object in array
     * */
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

    /**
     * Method to check last index of object in array
     * */
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

    /**
     * Method to remove by index
     * */
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

    /**
     * Method to remove first element
     * */
    public void removeFirst() {
        this.first = this.first.getNext();
        this.first.setPrev(null);
        this.size--;
    }

    /**
     * Method to remove last element
     * */
    public void removeLast() {
        this.last = this.last.getPrev();
        this.last.setNext(null);
        this.size--;
    }

    /**
     * Add value to the end of list
     * */
    public void push(T obj) {
        addLast(obj);
    }

    /**
     * Get last value and remove it from the list
     * */
    public T pop() {
        T result = this.last.getValue();
        removeLast();
        return result;
    }

    /**
     * Get current head of the list and remove
     * */
    public T poll() {
        T result = this.first.getValue();
        removeFirst();
        return result;
    }

    /**
     * Method to clear array
     * */
    public void clear() {
        this.first = this.last = null;
        this.size = 0;
    }

    /**
     * Method to get array size
     * */
    public int size() {
        return this.size;
    }

    /**
     * Method to cast linkedList to object array
     * */
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

    /**
     * Iterator implementation
     * */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = first;

            /**
             * Method to check if next exist
             * */
            @Override
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Method to iterate through array
             * */
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
