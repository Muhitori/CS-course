package com.shpp.p2p.cs.kturevich.assignment15;

import java.util.ArrayList;

public class Huffman {
    private Byte[] byteArray;
    private ArrayList<Frequency> frequencyTable;

    Huffman(Byte[] byteArray) {
        this.byteArray = byteArray;
        setFrequencyTable();
    }

    private void setFrequencyTable() {
        frequencyTable = new ArrayList<>();

        for (Byte b : byteArray) {
            Frequency temp = containsValue(frequencyTable, b);
            if (temp != null) {
                temp = frequencyTable.get(frequencyTable.indexOf(temp));
                temp.setFrequency(temp.getFrequency() + 1);
                continue;
            }
            frequencyTable.add(new Frequency(new Node(b), 1));
        }
    }

    public Node buildHuffmanTree () {
        while (frequencyTable.size() > 1) {
            Node newNode = new Node(null);

            Frequency first = minimumNode(frequencyTable);
            Node firstNode = first.getNode();
            frequencyTable.remove(first);
            firstNode.setParent(newNode);

            Frequency second = minimumNode(frequencyTable);
            Node secondNode = second.getNode();
            frequencyTable.remove(second);
            secondNode.setParent(newNode);

            newNode.setLeft(firstNode);
            newNode.setRight(secondNode);
            frequencyTable.add(new Frequency(newNode, first.getFrequency()+ second.getFrequency()));
        }
        return frequencyTable.get(0).getNode();
    }

    private Frequency minimumNode(ArrayList<Frequency> arr) {
        Frequency minimum = arr.get(0);
        for (Frequency frequency : arr) {
            if (frequency.getFrequency() < minimum.getFrequency()) {
                minimum = frequency;
            }
        }
        return minimum;
    }

    private Frequency containsValue (ArrayList<Frequency> arr, Byte c) {
        for (Frequency frequency : arr) {
            if (frequency.getNode().getValue().equals(c)) {
                return frequency;
            }
        }
        return null;
    }

    public static void printTree(Node node) {
        if (node.getParent() == null) {
            System.out.println("Root: " + node);
        } else {
            System.out.println(node);
        }

        if (node.getLeft() != null) {
            printTree(node.getLeft());
        }
        if (node.getRight() != null) {
            printTree(node.getRight());
        }
    }

    private static boolean areEqual(Node a, Node b) {
        return a.getParent().getValue().equals(b.getParent().getValue()) &&
                a.getLeft().getValue().equals(b.getLeft().getValue()) &&
                a.getRight().getValue().equals(b.getRight().getValue());
    }
}
