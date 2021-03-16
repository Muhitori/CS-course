package com.shpp.p2p.cs.kturevich.assignment16;

import java.util.Arrays;

public class Assignment16Part1 {

    public static void main(String[] args) {
        try{
            arrayListCases();
            linkedListCases();
            stackCases();
            queueCases();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ArrayList testing
    private static void arrayListCases() {
        System.out.println("~~~~~~~~~~~~~~ARRAYLIST~~~~~~~~~~~~~~~~~~");
        MyArrayList<Integer> ints = new MyArrayList<>();

        //Exception
        //bytes.remove(0);
        //ints.get(0);

        System.out.println("Index of 3 is: " + ints.indexOf(3));
        System.out.println("Contains 3 is: " + ints.contains(3));
        System.out.println("Size: " + ints.size());

        ints.add(1);
        ints.add(2);
        ints.add(3);
        System.out.println("Added 1, 2, 3: " + Arrays.toString(ints.toArray()));
        System.out.println("Size: " + ints.size());

        //Exception
        //ints.add(10, 1);
        //ints.get(10);

        ints.add(1, 10);
        System.out.println("Added 10 as second element: " + Arrays.toString(ints.toArray()));
        System.out.println("Get element on index 3: " + ints.get(3));
        System.out.println("Size: " + ints.size());

        System.out.println("Index of 3 is: " + ints.indexOf(3));
        System.out.println("Index of 4 is: " + ints.indexOf(4));

        System.out.println("Contains 3 is: " + ints.contains(3));
        System.out.println("Contains of 4 is: " + ints.contains(4));

        ints.add(1);
        System.out.println("Added 1: " + Arrays.toString(ints.toArray()));
        System.out.println("Last index of 1: " + ints.lastIndexOf(1));

        ints.clear();
        System.out.println("Clear: " + Arrays.toString(ints.toArray()));
        System.out.println("Size: " + ints.size());

        //Exception
        //bytes.remove(0);
        //ints.get(0);
    }

    //LinkedList testing
    private static void linkedListCases() {
        System.out.println("~~~~~~~~~~~~~~LINKEDLIST~~~~~~~~~~~~~~~~~~");
        MyLinkedList<Integer> ints = new MyLinkedList<>();

        //Exception
        //bytes.remove(0);
        //ints.get(0);
        //ints.getFirst();
        //ints.getLast();
        //ints.removeFirst();
        //ints.removeLast();

        System.out.println("Index of 3 is: " + ints.indexOf(3));
        System.out.println("Contains 3 is: " + ints.contains(3));
        System.out.println("Size: " + ints.size());

        ints.add(1);
        ints.add(2);
        ints.add(3);
        System.out.println("Added 1, 2, 3: " + Arrays.toString(ints.toArray()));
        System.out.println("Size: " + ints.size());

        //Exception
        //ints.add(10, 1);
        //ints.get(10);

        ints.add(1, 10);
        System.out.println("Added 10 as second element: " + Arrays.toString(ints.toArray()));
        System.out.println("Get element on index 3: " + ints.get(3));
        System.out.println("Size: " + ints.size());

        System.out.println("Index of 3 is: " + ints.indexOf(3));
        System.out.println("Index of 4 is: " + ints.indexOf(4));

        System.out.println("Contains 3 is: " + ints.contains(3));
        System.out.println("Contains of 4 is: " + ints.contains(4));

        ints.push(1);
        System.out.println("Pushed 1: " + Arrays.toString(ints.toArray()));
        System.out.println("Last index of 1: " + ints.lastIndexOf(1));

        System.out.println("List: " + Arrays.toString(ints.toArray()));
        System.out.println("Popped: " + ints.pop());

        System.out.println("List: " + Arrays.toString(ints.toArray()));

        System.out.println("Polled: " + ints.poll());
        System.out.println("List: " + Arrays.toString(ints.toArray()));

        ints.clear();
        System.out.println("Clear: " + Arrays.toString(ints.toArray()));
        System.out.println("Size: " + ints.size());

        //Exception
        //bytes.remove(0);
        //ints.get(0);
        //ints.getFirst();
        //ints.getLast();
        //ints.removeFirst();
        //ints.removeLast();
    }

    //Stack testing
    private static void stackCases() {
        System.out.println("~~~~~~~~~~~~~~STACK~~~~~~~~~~~~~~~~~~");
        MyStack<Integer> ints = new MyStack<>();

        //Exception
        //ints.pop();
        System.out.println("Peeked value: " + ints.peek()); //null
        System.out.println("Empty: " + ints.isEmpty());

        ints.push(1);
        ints.push(2);
        ints.push(3);
        System.out.println("Added 1, 2, 3: " + Arrays.toString(ints.toArray()));

        while (!ints.isEmpty()) {
            System.out.println("Popped: " + ints.pop());
            System.out.println("Stack: " + Arrays.toString(ints.toArray()));
            System.out.println("Empty: " + ints.isEmpty());
        }

        //Exception
        //ints.pop();
        System.out.println("Peeked value: " + ints.peek()); //null
    }

    //Queue testing
    private static void queueCases() {
        System.out.println("~~~~~~~~~~~~~~QUEUE~~~~~~~~~~~~~~~~~~");

        MyQueue<Integer> ints = new MyQueue<>();

        //Exception
        //ints.poll();
        System.out.println("Peeked value: " + ints.peek()); //null
        System.out.println("Empty: " + ints.isEmpty());

        ints.add(1);
        ints.add(2);
        ints.add(3);
        System.out.println("Added 1, 2, 3: " + Arrays.toString(ints.toArray()));

        while (!ints.isEmpty()) {
            System.out.println("Polled: " + ints.poll());
            System.out.println("Queue: " + Arrays.toString(ints.toArray()));
            System.out.println("Empty: " + ints.isEmpty());
        }

        //Exception
        //ints.poll();
        System.out.println("Peeked value: " + ints.peek()); //null
    }
}
