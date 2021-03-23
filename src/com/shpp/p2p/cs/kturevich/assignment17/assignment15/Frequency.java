package com.shpp.p2p.cs.kturevich.assignment17.assignment15;

public class Frequency implements Comparable<Frequency> {
    private final Node node;
    private int frequency;

    Frequency(Node node, int frequency) {
        this.node = node;
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getNode() {
        return this.node;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Frequency{" +
                "value=" + node +
                ", frequency=" + frequency +
                '}';
    }

    @Override
    public int compareTo(Frequency o) {
        if (o.getFrequency() > frequency)
            return -1;
        else if(o.getFrequency() < frequency)
            return 1;
        return 0;
    }
}
