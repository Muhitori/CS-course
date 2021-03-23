package com.shpp.p2p.cs.kturevich.assignment17;

public class Entry<K, V> {
    private K key;
    private V value;
    private Entry<K, V> next;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public boolean equals(Entry<K, V> entry) {
        return entry.getKey().equals(this.key) && entry.getValue().equals(this.value);
    }
}
