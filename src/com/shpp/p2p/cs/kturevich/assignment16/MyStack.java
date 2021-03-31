package com.shpp.p2p.cs.kturevich.assignment16;

import java.util.Iterator;

/**
 * Stack implementation
 * */
@SuppressWarnings("unchecked")
public class MyStack<T> implements Iterable<T>{
    private T[] stack = (T[]) new Object[0];

    /**
     * Add value to the end of stack
     * */
    public void push(T element) {
        T[] temp = (T[]) new Object[this.stack.length + 1];
        System.arraycopy(stack, 0, temp, 0, stack.length);
        temp[temp.length - 1] = element;
        stack = temp;
    }

    /**
     * Get current value without removing it
     * */
    public T peek() {
        return stack.length == 0 ? null : stack[stack.length - 1];
    }

    /**
     * Get current value and remove it from the stack
     * */
    public T pop() {
        if (stack.length == 0)
            throw new IndexOutOfBoundsException();

        T result = stack[stack.length - 1];

        T[] temp = (T[]) new Object[this.stack.length - 1];
        System.arraycopy(stack, 0, temp, 0, temp.length);
        stack = temp;

        return result;
    }

    /**
     * Check if queue is empty
     * */
    public boolean isEmpty() {
        return stack.length == 0;
    }

    /**
     * Method to cast stack to object array
     * */
    public T[] toArray() {
        return stack;
    }

    /**
     * Iterator implementation
     * */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;

            /**
             * Method to check if next exist
             * */
            @Override
            public boolean hasNext() {
                return i < stack.length;
            }

            /**
             * Method to iterate through array
             * */
            @Override
            public T next() {
                if (hasNext()) {
                    T value = stack[i];
                    i++;
                    return value;
                }
                return null;
            }
        };
    }
}
