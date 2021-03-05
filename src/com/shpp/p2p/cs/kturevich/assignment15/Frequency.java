package com.shpp.p2p.cs.kturevich.assignment15;

public class Frequency<T> {
    private final T value;
    private int frequency;

    Frequency(T value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public T getValue() {
        return value;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Frequency{" +
                "node=" + value +
                ", frequency=" + frequency +
                '}';
    }
}
