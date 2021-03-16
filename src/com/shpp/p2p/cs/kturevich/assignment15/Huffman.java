package com.shpp.p2p.cs.kturevich.assignment15;

import java.util.ArrayList;

//Class for Huffman methods
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

            //if this node is exist in table - add 1 to its value
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

            //Get nodes with smallest values...
            Frequency first = minimumNode(frequencyTable);
            Node firstNode = first.getNode();
            frequencyTable.remove(first);

            Frequency second = minimumNode(frequencyTable);
            Node secondNode = second.getNode();
            frequencyTable.remove(second);

            //...and add them to new node which value is summarized from its children
            firstNode.setParent(newNode);
            secondNode.setParent(newNode);

            newNode.setLeft(firstNode);
            newNode.setRight(secondNode);
            frequencyTable.add(new Frequency(newNode, first.getFrequency()+ second.getFrequency()));
        }
        return frequencyTable.get(0).getNode();
    }

    //Return node with smallest value
    private Frequency minimumNode(ArrayList<Frequency> arr) {
        Frequency minimum = arr.get(0);
        for (Frequency frequency : arr) {
            if (frequency.getFrequency() < minimum.getFrequency()) {
                minimum = frequency;
            }
        }
        return minimum;
    }

    //Check if node already exists in table
    private Frequency containsValue (ArrayList<Frequency> arr, Byte c) {
        for (Frequency frequency : arr) {
            if (frequency.getNode().getValue().equals(c)) {
                return frequency;
            }
        }
        return null;
    }
}
