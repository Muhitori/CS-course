package com.shpp.p2p.cs.kturevich.assignment17;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class MyPriorityQueue<T extends Comparable<T>> implements Iterable<T>{
    private T[] array;

    /**
     * MyPriorityQueue Empty constructor
     * */
    public MyPriorityQueue() {
        this.array = (T[]) new Comparable[0];
    }

    /**
     * MyPriorityQueue constructor with array as argument
     * */
    public MyPriorityQueue(T[] heap) {
        this.array = heap;
        buildHeap();
    }

    /**
     * Builds heap from array
     * */
    private void buildHeap() {
        for (int i = array.length / 2; i >= 0; i--) {
            heapify(array, i);
        }
    }

    /**
     * Method to cast array to heap starting from some index
     * */
    private void heapify(T[] arr, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int parent = i;

        //while left < heap.length and heap[left] < heap[parent]
        if (left < arr.length && arr[left].compareTo(arr[parent]) < 0) {
            parent = left;
        }

        //while right < heap.length and heap[right] < heap[parent]
        if (right < arr.length && arr[right].compareTo(arr[parent]) < 0) {
            parent = right;
        }

        if (i != parent) {
            swap(arr, parent, i);
            heapify(arr, parent);
        }
    }

    /**
     * Method to add element to specified index
     * */
    private void increaseKey(T[] arr, int i, T el) {
        if (el == null)
            throw new NullPointerException();

        arr[i] = el;

        //while i > 0 and arr[(i - 1)/2] > arr[i]
        while (i > 0 && arr[(i - 1) / 2].compareTo(arr[i]) > 0) {
            swap(arr, (i - 1) / 2, i);

            i = (i - 1)/2;
        }
    }

    /**
     * Method to add
     * */
    public void add(T el) throws Exception {
        T[] newArr = Arrays.copyOf(this.array, this.array.length + 1);
        increaseKey(newArr, newArr.length - 1, el);
        array = newArr;
    }

    /**
     * Get current head of the queue without removing
     * */
    public T peek() {
        return array.length == 0 ? null : array[0];
    }

    /**
     * Get current head of the queue and remove
     * */
    public T poll() {
        if (array.length == 0)
            throw new IndexOutOfBoundsException();

        T[] temp = (T[]) new Comparable[array.length - 1];
        T result = this.array[0];
        array[0] = array[array.length - 1];

        System.arraycopy(array, 0, temp, 0, temp.length);
        heapify(temp, 0);

        array = temp;
        return result;
    }

    /**
     * Check if queue is empty
     * */
    public boolean isEmpty() {
        return this.array.length == 0;
    }

    /**
     * Get queue size
     * */
    public int size() {
        return this.array.length;
    }

    /**
     * Cast queue to array (im using it for testing)
     * */
    public T[] toArray() {
        return array;
    }

    /**
     * Method to swap elements
     * */
    private void swap(T[] arr, int firstIndex, int secondIndex) {
        T temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
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
