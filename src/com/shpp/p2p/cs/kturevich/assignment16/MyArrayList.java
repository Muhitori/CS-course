package com.shpp.p2p.cs.kturevich.assignment16;

//ArrayList implementation
@SuppressWarnings("unchecked")
public class MyArrayList<T>{
    private T[] array;

    MyArrayList() {
        this.array = (T[]) new Object[0];
    }

    //Methods to add
    public void add(T element) {
        T[] temp = (T[]) new Object[this.array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[temp.length - 1] = element;
        array = temp;
    }

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

    public T get(int index) {
        return this.array[index];
    }

    //Methods to elements check
    public boolean contains(T obj) {
        for(T el : array) {
            if (el == obj)
                return true;
        }
        return false;
    }

    public int indexOf(T obj) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == obj)
                return i;
        }
        return -1;
    }

    public int lastIndexOf(T obj) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == obj)
                result =  i;
        }
        return result;
    }

    //Methods to remove
    public void remove(int index) {
        if (array.length == 0)
            throw new IndexOutOfBoundsException();

        T[] temp = (T[]) new Object[this.array.length - 1];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = i < index ? array[i] : array[i + 1];
        }
        this.array = temp;
    }

    public void clear() {
        this.array = (T[]) new Object[0];
    }

    public int size() {
        return this.array.length;
    }

    public T[] toArray() {
        return  this.array;
    }
}
