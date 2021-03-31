package com.shpp.p2p.cs.kturevich.assignment16;

import java.util.Iterator;

/**
 * ArrayList implementation
 * */
@SuppressWarnings("unchecked")
public class MyArrayList<T> implements Iterable<T>{
    private T[] array;

    MyArrayList() {
        this.array = (T[]) new Object[0];
    }

    /**
     * Method to add
     * */
    public void add(T element) {
        T[] temp = (T[]) new Object[this.array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[temp.length - 1] = element;
        array = temp;
    }

    /**
     * Method to add by index
     * */
    public void add(int index, T element ) {
        T[] temp = (T[]) new Object[this.array.length + 1];

        for (int i = 0; i < temp.length; i++) {
            if (i == index) {
                temp[i] = element;
                continue;
            }

            if (i < index) {
                temp[i] = array[i];
            } else {
                temp[i] = array[i - 1];
            }
        }
        array = temp;
    }

    /**
     * Method to get by index
     * */
    public T get(int index) {
        return this.array[index];
    }

    /**
     * Method to check if object exist in array
     * */
    public boolean contains(T obj) {
        for(T el : array) {
            if (el == obj)
                return true;
        }
        return false;
    }

    /**
     * Method to check index of object in array
     * */
    public int indexOf(T obj) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == obj)
                return i;
        }
        return -1;
    }

    /**
     * Method to check last index of object in array
     * */
    public int lastIndexOf(T obj) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == obj)
                result =  i;
        }
        return result;
    }

    /**
     * Method to remove by index
     * */
    public void remove(int index) {
        if (size() == 0)
            throw new IndexOutOfBoundsException();

        T[] temp = (T[]) new Object[size() - 1];

        for (int i = 0; i < array.length; i++) {
            if (i == index) {
                continue;
            }

            if (i < index)
                temp[i] = array[i];
            else
                temp[i - 1] = array[i];
        }

        this.array = temp;
    }

    /**
     * Method to clear array
     * */
    public void clear() {
        this.array = (T[]) new Object[0];
    }

    /**
     * Method to get array size
     * */
    public int size() {
        return this.array.length;
    }

    /**
     * Method to cast arraylist to object array
     * */
    public T[] toArray() {
        return  this.array;
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
                return i < array.length;
            }

            /**
             * Method to iterate through array
             * */
            @Override
            public T next() {
                if (hasNext()) {
                    T value = array[i];
                    i++;
                    return value;
                }
                return null;
            }
        };
    }
}
