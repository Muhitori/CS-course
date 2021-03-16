package com.shpp.p2p.cs.kturevich.assignment17.assignment16;

import java.util.Iterator;

//Queue implementation
@SuppressWarnings("unchecked")
public class MyQueue<T> implements Iterable<T>{
    private T[] queue = (T[]) new Object[0];

    //Add value as head of the queue
    public void add(T element) {
        T[] temp = (T[]) new Object[queue.length + 1];
        temp[0] = element;
        System.arraycopy(queue, 0, temp, 1, queue.length);
        queue = temp;
    }

    //Get current head of the queue without removing
    public T peek() {
        return queue.length == 0 ? null : queue[0];
    }

    //Get current head of the queue and remove
    public T poll() {
        if (queue.length == 0)
            throw new IndexOutOfBoundsException();

        T[] temp = (T[]) new Object[queue.length - 1];
        T result = this.queue[0];
        System.arraycopy(queue, 1, temp, 0, temp.length);

        queue = temp;
        return result;
    }

    public boolean isEmpty() {
        return this.queue.length == 0;
    }

    public T[] toArray() {
        return queue;
    }

    //Iterator implementation
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < queue.length;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T value = queue[i];
                    i++;
                    return value;
                }
                return null;
            }
        };
    }
}
