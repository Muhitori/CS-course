package com.shpp.p2p.cs.kturevich.assignment17;

import com.shpp.p2p.cs.kturevich.assignment17.assignment11.Assignment11Part1;
import com.shpp.p2p.cs.kturevich.assignment17.assignment13.Assignment13Part1;
import com.shpp.p2p.cs.kturevich.assignment17.assignment15.Assignment15Part1;

import java.util.Arrays;

public class Test {

    /**
     * HashMap cases
     * */
    public void hashmapCases() {
        System.out.println("~~~~~~~~~~~~~~HASHMAP~~~~~~~~~~~~~~~~~~");

        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        printResult("Empty hashmap", compare(hashMap.tableSize(), 2));

        hashMap.put("a", 1);
        hashMap.put("b", 1);
        hashMap.put("e", 1);
        hashMap.put("e", 2);
        hashMap.put("eh", 20);
        hashMap.put("ehe", 21);
        hashMap.put("d", 20);
        hashMap.put("f", 21);

        System.out.println("Added 8 elements to hashmap...");
        printResult("Extend hashmap", compare(hashMap.tableSize(), 8));
        printResult("Collision", compare(hashMap.entrySet().length, 7));

        String[] keys = {"a", "b", "ehe", "eh", "d", "e", "f"};
        Integer[] values = {1, 1, 21, 20, 20, 2, 21};

        printResult("Hashmap keys", compare(hashMap.keySet(), keys));
        printResult("Hashmap values", compare(hashMap.values(), values));

        printResult("Get", compare(hashMap.get("e"), 2));

        printResult("Contains", compare(hashMap.containsKey("eh"), true));
        hashMap.remove("eh");
        printResult("Remove", compare(hashMap.containsKey("eh"), false));
    }

    /**
     * PriorityQueue cases
     * */
    public void priorityQueueCases() throws Exception {
        System.out.println("~~~~~~~~~~~~~~PRIORITYQUEUE~~~~~~~~~~~~~~~~~~");

        MyPriorityQueue<Integer> ints = new MyPriorityQueue<>();

        printResult("Empty", compare(ints.isEmpty(), true));
        printResult("Empty peek", compare(ints.peek(), null));

        ints.add(1);
        ints.add(3);
        ints.add(5);
        printResult("Added 1, 3, 5", compare(new Integer[]{1, 3, 5}, ints.toArray()));

        ints.add(0);
        printResult("Added 0", compare(new Integer[]{0, 1, 5, 3}, ints.toArray()));

        ints.add(2);
        printResult("Added 2", compare(new Integer[]{0, 1, 3, 5, 2}, ints.toArray()));

        ints.add(10);
        printResult("Added 10", compare(new Integer[]{0, 1, 3, 5, 2, 10}, ints.toArray()));

        ints.add(4);
        printResult("Added 4", compare(new Integer[]{0, 1, 4, 3, 2, 10, 5}, ints.toArray()));

        Integer[] integers = {0, 1, 2, 3, 4, 5, 10};
        int i = 0;

        System.out.println("Polling all values...");
        while (!ints.isEmpty()) {
            printResult("Poll", compare(ints.poll(), integers[i]));
            i++;
        }

        printResult("Empty", compare(ints.isEmpty(), true));
        printResult("Empty peek", compare(ints.peek(), null));
    }

    /**
     * Calculator cases
     * */
    public void assignment11Cases() {
        System.out.println("~~~~~~~~~~~~~~CALCULATOR~~~~~~~~~~~~~~~~~~");

        Assignment11Part1 calculator;
        String[][] formulas = {
                {"2 + 2"},
                {"2 + a", "a = 2"},

                {"(2 + 2)"},
                {"(2 + 2) * 2"},
                {"2 + (2 + 2) * 2"},

                {"2^3^2"},
                {"(2^3)^2"}
        };

        double[] values = {
                4.0,
                4.0,

                4.0,
                8.0,
                10.0,

                512.0,
                64
        };

        for (int i = 0; i < formulas.length; i++) {
            calculator = new Assignment11Part1(formulas[i]);
            printResult(formulas[i][0], compare(calculator.getResult(), values[i]));
        }
    }

    /**
     * Silhouette finder cases
     * */
    public void assignment13Cases() {
        System.out.println("~~~~~~~~~~~~~~SILHOUETTE FINDER~~~~~~~~~~~~~~~~~~");
        Assignment13Part1 silhouetteFinder;

        String[][] images = {
                {"assets/13.png"},
                {"assets/eheh.jpg"},
                {"assets/eh.jpg"}
        };

        int[] values = {5, 6, 5};

        for (int i = 0; i < images.length; i++) {
            silhouetteFinder = new Assignment13Part1(images[i]);
            printResult(images[i][0], compare(silhouetteFinder.getResult(), values[i]));
        }
    }

    /**
     * Huffman archiver cases
     * */
    public void assignment15Cases() throws Exception {
        System.out.println("~~~~~~~~~~~~~~HUFFMAN ARCHIVER~~~~~~~~~~~~~~~~~~");
        Assignment15Part1 archiver;

        String[][] parameters = {
                {"assets/example2.txt"},
                {"assets/example2.txt.par"},
                {"assets/dictionary.txt"},
                {"assets/dictionary.txt.par"},
        };

        long[] values = {20, 10, 628845, 1176877};

        for (int i = 0; i < parameters.length; i++) {
            archiver = new Assignment15Part1(parameters[i]);
            System.out.println();
            printResult(parameters[i][0], compare(archiver.getResult(), values[i]));
            System.out.println();
        }
    }

    //Method to compare objects
    private boolean compare(Object value, Object desiredValue) {
        if (value == null && desiredValue == null) {
            return true;
        }

        return  value.equals(desiredValue);
    }

    //Method to compare arrays
    private boolean compare(Object[] value, Object[] desiredValue) {
        return Arrays.equals(value, desiredValue);
    }

    //Print test result
    private void printResult(String testName, boolean pass) {
        if (pass) {
            System.out.println(" PASS " + testName);
        } else {
            System.out.println("! FAIL " + testName);
        }
    }
}
