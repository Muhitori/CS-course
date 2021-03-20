package com.shpp.p2p.cs.kturevich.assignment17;

import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyQueue;

import java.util.Arrays;

public class Assignment17Part1 {

    public static void main(String[] args) throws Exception {
        try {
            hashmapCases();
            //priorityQueueCases();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hashmapCases() {
        System.out.println("~~~~~~~~~~~~~~HASHMAP~~~~~~~~~~~~~~~~~~");

        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        System.out.println(hashMap.tableSize());

        hashMap.put("a", 1);
        hashMap.put("b", 1);
        System.out.println(hashMap.tableSize());

        hashMap.put("e", 1);
        hashMap.put("e", 2);
        System.out.println(hashMap.tableSize());

        hashMap.put("eh", 20);
        hashMap.put("ehe", 21);
        System.out.println(hashMap.tableSize());

        hashMap.put("d", 20);
        hashMap.put("f", 21);
        System.out.println(hashMap.tableSize());

        System.out.println(Arrays.toString(hashMap.entrySet()));
        System.out.println(Arrays.toString(hashMap.keySet()));
        System.out.println(Arrays.toString(hashMap.values()));

        System.out.println(hashMap.get("eh"));
        System.out.println(hashMap.containsKey("eh"));
        hashMap.remove("eh", 20);
        System.out.println(hashMap.containsKey("eh"));
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
