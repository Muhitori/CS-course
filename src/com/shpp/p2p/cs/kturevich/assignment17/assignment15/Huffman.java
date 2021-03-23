package com.shpp.p2p.cs.kturevich.assignment17.assignment15;

import com.shpp.p2p.cs.kturevich.assignment17.MyPriorityQueue;
import com.shpp.p2p.cs.kturevich.assignment17.assignment16.MyArrayList;

//Class for Huffman methods
public class Huffman {
    private Byte[] byteArray;
    private MyArrayList<Frequency> frequencyTable;
    private MyPriorityQueue<Frequency> queue = new MyPriorityQueue<>();

    Huffman(Byte[] byteArray) throws Exception {
        this.byteArray = byteArray;
        setFrequencyTable();
    }

    private void setFrequencyTable() throws Exception {
        frequencyTable = new MyArrayList<>();

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

        for (Frequency f : frequencyTable) {
            queue.add(f);
        }
    }

    public Node buildHuffmanTree () throws Exception {
        while (queue.size() > 1) {
            Node newNode = new Node(null);

            //Get nodes with smallest values...
            Frequency first = queue.poll();
            Node firstNode = first.getNode();

            Frequency second =  queue.poll();;
            Node secondNode = second.getNode();

            //...and add them to new node which value is summarized from its children
            firstNode.setParent(newNode);
            secondNode.setParent(newNode);

            newNode.setLeft(firstNode);
            newNode.setRight(secondNode);
            queue.add(new Frequency(newNode, first.getFrequency()+ second.getFrequency()));
        }
        return queue.poll().getNode();
    }

    //Check if node already exists in table
    private Frequency containsValue (MyArrayList<Frequency> arr, Byte c) {
        for (Frequency frequency : arr) {
            if (frequency.getNode().getValue().equals(c)) {
                return frequency;
            }
        }
        return null;
    }
}
