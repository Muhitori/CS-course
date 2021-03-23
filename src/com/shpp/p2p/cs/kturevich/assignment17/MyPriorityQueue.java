package com.shpp.p2p.cs.kturevich.assignment17;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class MyPriorityQueue<T extends Comparable<T>> implements Iterable<T>{
    T[] array;

    public MyPriorityQueue() {
        this.array = (T[]) new Comparable[0];
    }

    MyPriorityQueue(T[] heap) {
        this.array = heap;
        buildHeap();
    }

    //Builds heap from array
    private void buildHeap() {
        for (int i = array.length / 2; i >= 0; i--) {
            heapify(array, i);
        }
    }

    //Method to cast array to heap starting from some index
    private void heapify(T[] heap, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int min = i;

        //while left < heap.length and heap[left] < heap[min]
        if (left < heap.length && heap[left].compareTo(heap[min]) < 0) {
            min = left;
        }

        //while right < heap.length and heap[right] < heap[min]
        if (right < heap.length && heap[right].compareTo(heap[min]) < 0) {
            min = right;
        }

        if (i != min) {
            T temp = heap[i];
            heap[i] = heap[min];
            heap[min] = temp;

            heapify(heap, min);
        }
    }

    private void increaseKey(T[] arr, int i, T el) {
        if (el == null)
            throw new NullPointerException();

        arr[i] = el;

        //while i > 0 and arr[i/2] > arr[i]
        while (i > 0 && arr[i/2].compareTo(arr[i]) > 0) {
            T temp = arr[i/2];
            arr[i/2] = arr[i];
            arr[i] = temp;

            i = i / 2;
        }
    }

    public void add(T el) throws Exception {
        T[] newArr = Arrays.copyOf(this.array, this.array.length + 1);
        increaseKey(newArr, newArr.length - 1, el);
        array = newArr;
    }

    //Get current head of the queue without removing
    public T peek() {
        return array.length == 0 ? null : array[0];
    }

    //Get current head of the queue and remove
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

    public boolean isEmpty() {
        return this.array.length == 0;
    }

    public int size() {
        return this.array.length;
    }

    public T[] toArray() {
        return array;
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
