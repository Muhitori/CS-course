package com.shpp.p2p.cs.kturevich.assignment17.assignment16;

import java.util.Arrays;
import java.util.Iterator;

//ArrayList implementation
@SuppressWarnings("unchecked")
public class MyArrayList<T> implements Iterable<T>{

    private class SubList extends MyArrayList<T> {
        private final MyArrayList<T> parent;
        private int start;
        private int size;

        public SubList(MyArrayList<T> parent, int start, int end){
            this.parent = parent;
            this.start = start;
            this.size = end - start;
        }

        public void add(int index, T object){
            checkBounds(index);
            this.parent.add(start + index, object);
        }

        public T get(int index) {
            checkBounds(index);
            return this.parent.get(start + index);
        }

        public void set(int index, T value) {
            checkBounds(index);
            this.parent.set(start + index, value);
        }

        public SubList subList(int fromIndex, int toIndex) {
            fromIndex += start;
            toIndex += start;

            SubList result = new SubList(this, fromIndex, toIndex);

            for (int i = fromIndex; i < toIndex; i++) {
                result.add(this.parent.get(i));
            }

            return result;
        }

        public void remove(int index) {
            checkBounds(index);
            this.parent.remove(start + index);
        }

        private void checkBounds(int index) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException();
        }
    }

    private T[] array;

    public MyArrayList() {
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
        if (index > size() || index < 0)
            throw new IndexOutOfBoundsException();

        return this.array[index];
    }

    public void set(int index, T value) {
        this.array[index] = value;
    }

    public SubList subList(int fromIndex, int toIndex) {
        SubList result = new SubList(this, fromIndex, toIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            result.add(this.array[i]);
        }
        return result;
    }

    //Methods to elements check
    public boolean contains(T obj) {
        for(T el : array) {
            if (el.equals(obj))
                return true;
        }
        return false;
    }

    public int indexOf(T obj) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(T obj) {
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(obj))
                result =  i;
        }
        return result;
    }

    //Methods to remove
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

    public void remove(T obj) {
        int index = indexOf(obj);

        if (index != -1)
            remove(index);
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

    @Override
    public String toString() {
        return "MyArrayList{" + Arrays.toString(array) + '}';
    }

    //Iterator implementation
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < array.length;
            }

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
