package com.shpp.p2p.cs.kturevich.assignment17;

import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyQueue;

import java.util.Arrays;

public class Assignment17Part1 {

    public static void main(String[] args) throws Exception {
        try {
            priorityQueueCases();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Queue testing
    private static void priorityQueueCases() throws Exception {
        System.out.println("~~~~~~~~~~~~~~PRIORITYQUEUE~~~~~~~~~~~~~~~~~~");

        MyPriorityQueue<Integer> ints = new MyPriorityQueue<>();

        //Exception
        //ints.poll();
        System.out.println("Peeked value: " + ints.peek()); //null
        System.out.println("Empty: " + ints.isEmpty());

        ints.add(1);
        ints.add(3);
        ints.add(5);
        System.out.println("Added 1, 3, 5: " + Arrays.toString(ints.toArray()));

        ints.add(0);
        System.out.println("Added 0: " + Arrays.toString(ints.toArray()));

        ints.add(2);
        System.out.println("Added 2: " + Arrays.toString(ints.toArray()));

        ints.add(10);
        System.out.println("Added 10: " + Arrays.toString(ints.toArray()));

        ints.add(4);
        System.out.println("Added 4: " + Arrays.toString(ints.toArray()));

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
