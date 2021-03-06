package com.shpp.p2p.cs.kturevich.assignment15;

import java.util.ArrayList;

public class Huffman {
    private Byte[] byteArray;

    Huffman(Byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public void main() {
        ArrayList<Frequency> frequencyTable = new ArrayList<>();

        for (Byte b : byteArray) {
            Frequency temp = containsValue(frequencyTable, b);
            if (temp != null) {
                temp = frequencyTable.get(frequencyTable.indexOf(temp));
                temp.setFrequency(temp.getFrequency() + 1);
                continue;
            }
            frequencyTable.add(new Frequency(new Node(b), 1));
        }

        Node HuffmanTree = buildHuffmanTree(frequencyTable);

        dfsHuffman(HuffmanTree);
    }


    private void dfsHuffman(Node node) {
        if (node.getParent() == null) {
            System.out.println("Root: " + node);
        } else {
            System.out.println(node);
        }

        if (node.getLeft() != null) {
            dfsHuffman(node.getLeft());
        }
        if (node.getRight() != null) {
            dfsHuffman(node.getRight());
        }
    }

    private Node buildHuffmanTree (ArrayList<Frequency> nodesNodeFrequency) {
        while (nodesNodeFrequency.size() > 1) {
            Node newNode = new Node(null);

            Frequency first = minimumNode(nodesNodeFrequency);
            Node firstNode = first.getNode();
            nodesNodeFrequency.remove(first);
            firstNode.setParent(newNode);

            Frequency second = minimumNode(nodesNodeFrequency);
            Node secondNode = second.getNode();
            nodesNodeFrequency.remove(second);
            secondNode.setParent(newNode);

            newNode.setLeft(firstNode);
            newNode.setRight(secondNode);
            nodesNodeFrequency.add(new Frequency(newNode, first.getFrequency()+ second.getFrequency()));
        }
        return nodesNodeFrequency.get(0).getNode();
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

    private static boolean areEqual(Node a, Node b) {
        return a.getParent().getValue().equals(b.getParent().getValue()) &&
                a.getLeft().getValue().equals(b.getLeft().getValue()) &&
                a.getRight().getValue().equals(b.getRight().getValue());
    }
}
