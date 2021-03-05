package com.shpp.p2p.cs.kturevich.assignment15;


import java.util.ArrayList;

public class Assignment15Part1 {

    public static void main(String[] args) {
        String initialString = "EWK";
        char[] test = initialString.toCharArray();

        ArrayList<Frequency<Character>> frequencyTable = new ArrayList<>();

        for (char c : test) {
            Frequency<Character> temp = containsValue(frequencyTable, c);
            if (temp != null) {
                temp = frequencyTable.get(frequencyTable.indexOf(temp));
                temp.setFrequency(temp.getFrequency() + 1);
            }
            frequencyTable.add(new Frequency<>(c, 1));
        }

        ArrayList<Frequency<Node<Character>>> nodesFrequency = new ArrayList<>();

        for (Frequency<Character> frequency : frequencyTable) {
            nodesFrequency.add(new Frequency<>(new Node<>(frequency.getValue()), frequency.getFrequency()));
        }

        Node HuffmanTree = buildHuffmanTree(nodesFrequency);

        dfsHuffman(HuffmanTree);
    }

    private static void dfsHuffman(Node node) {
        System.out.println(node);
        if (node.getLeft() != null)
            dfsHuffman(node.getLeft());
        if (node.getRight() != null)
            dfsHuffman(node.getRight());
    }

    private static Node buildHuffmanTree (ArrayList<Frequency<Node<Character>>> nodesNodeFrequency) {
        while (nodesNodeFrequency.size() > 1) {
            Node<Character> newNode = new Node<>('*');

            Frequency<Node<Character>> first = minimumNode(nodesNodeFrequency);
            Node<Character> firstNode = first.getValue();
            nodesNodeFrequency.remove(first);
            firstNode.setParent(newNode);

            Frequency<Node<Character>> second = minimumNode(nodesNodeFrequency);
            Node<Character> secondNode = second.getValue();
            nodesNodeFrequency.remove(second);
            secondNode.setParent(newNode);

            newNode.setLeft(firstNode);
            newNode.setRight(secondNode);
            nodesNodeFrequency.add(new Frequency<>(newNode, first.getFrequency()+ second.getFrequency()));
        }
        return nodesNodeFrequency.get(0).getValue();
    }

    private static Frequency<Node<Character>> minimumNode(ArrayList<Frequency<Node<Character>>> arr) {
        Frequency<Node<Character>> minimum = arr.get(0);
        for (Frequency<Node<Character>> frequency : arr) {
            if (frequency.getFrequency() < minimum.getFrequency()) {
                minimum = frequency;
            }
        }
        return minimum;
    }

    private static Frequency<Character> containsValue (ArrayList<Frequency<Character>> arr, char c) {
        for (Frequency<Character> frequency : arr) {
            if (frequency.getValue() == c) {
                return frequency;
            }
        }
        return null;
    }

    private static boolean areEqual(Node<Character> a, Node<Character> b) {
        return a.getParent().getValue().equals(b.getParent().getValue()) &&
                a.getLeft().getValue().equals(b.getLeft().getValue()) &&
                a.getRight().getValue().equals(b.getRight().getValue());
    }
}
