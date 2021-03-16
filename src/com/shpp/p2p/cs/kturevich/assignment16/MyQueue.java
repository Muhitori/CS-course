package com.shpp.p2p.cs.kturevich.assignment16;

//Queue implementation
@SuppressWarnings("unchecked")
public class MyQueue<T> {
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
}
