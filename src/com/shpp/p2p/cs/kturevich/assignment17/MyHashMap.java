package com.shpp.p2p.cs.kturevich.assignment17;

import com.shpp.p2p.cs.kturevich.assignment16.MyStack;

@SuppressWarnings("unchecked")
public class MyHashMap<K, V> {
    private int TABLE_SIZE = 2;
    private Object[] table;

    public MyHashMap() {
        this.table = new Object[TABLE_SIZE];
    }

    public void put(K key, V value) {
        int index = index(key.hashCode());

        if (table[index] != null) {
            collisionProcessing(key, value, index);
            return;
        }

        table[index] = new Entry<>(key, value);
        extend();
    }

    //Increase hashmap size if it full
    private void extend() {
        for (Object o : table) {
            if (o == null)
                return;
        }

        TABLE_SIZE *= 2;
        Object[] entries = entrySet();
        this.table = new Object[TABLE_SIZE];

        rebuild(entries);
    }

    //If indexes and keys of elements are equal, create chain of elements
    private void collisionProcessing(K key, V value, int index) {
        Entry<K, V> current = (Entry<K, V>) table[index];

        while (current.hasNext()) {
            if (current.getKey().equals(key)) {
                current.setValue(value);
                return;
            }

            current = current.getNext();
        }

        if (current.getKey().equals(key)) {
            current.setValue(value);
            return;
        }

        current.setNext(new Entry<>(key, value));
    }

    public V get(K key) {
        int index = index(key.hashCode());
        Entry<K, V> entry = (Entry<K, V>) table[index];

        if (entry == null) {
            return null;
        }

        if (!entry.hasNext()) {
            return entry.getValue();
        }

        while (entry.hasNext()) {
            if (entry.getKey().equals(key))
                return entry.getValue();
            entry = entry.getNext();
        }

        if (entry.getKey().equals(key))
            return entry.getValue();

        return null;
    }

    public boolean containsKey(K key) {
        int index = index(key.hashCode());
        Entry<K, V> entry = (Entry<K, V>) table[index];

        if (entry == null) {
            return false;
        }

        while (entry.hasNext()) {
            if (entry.getKey().equals(key))
                return true;
            entry = entry.getNext();
        }

        return entry.getKey().equals(key);
    }

    public void remove(K key, V value) {
        Object[] entries = entrySet();
        Entry<K, V> newEntry = new Entry<>(key, value);

        for (int i = 0; i < entries.length; i ++) {
            Entry<K,V> entry = (Entry<K, V>) entries[i];
            if(entry.equals(newEntry)) {
                entries[i] = null;
                break;
            }
        }

        this.table = new Object[TABLE_SIZE];

        rebuild(entries);
    }

    //Get list of keys
    public K[] keySet() {
        MyStack<K> result = new MyStack<>();

        for (Object entry : this.table) {
            if (entry != null) {
                Entry<K, V> current = (Entry<K, V>) entry;

                while (current.hasNext()) {
                    result.push(current.getKey());
                    current = current.getNext();
                }
                result.push(current.getKey());
            }
        }
        return result.toArray();
    }

    //Get list of values
    public V[] values() {
        MyStack<V> result = new MyStack<>();

        for (Object entry : this.table) {
            if (entry != null) {
                Entry<K, V> current = (Entry<K, V>) entry;

                while (current.hasNext()) {
                    result.push(current.getValue());
                    current = current.getNext();
                }
                result.push(current.getValue());
            }
        }
        return result.toArray();
    }

    //Get list of entries as object array
    public Object[] entrySet() {
        MyStack<Object> result = new MyStack<>();

        for (Object entry : this.table) {
            if (entry != null) {
                Entry<K, V> current = (Entry<K, V>) entry;

                while (current.hasNext()) {
                    result.push(current);
                    current = current.getNext();
                }
                result.push(current);
            }
        }
        return result.toArray();
    }

    private int index(int hash) {
        return hash & table.length - 1;
    }

    public int tableSize() {
        return this.table.length;
    }

    //Add all entries to hashmap
    private void rebuild(Object[] entries) {
        for (Object o : entries) {
            if (o != null) {
                Entry<K, V> entry = (Entry<K, V>) o;
                put(entry.getKey(), entry.getValue());
            }
        }
    }
}
