package _706;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Pair<U, V> {
    public U first;
    public V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}


class Bucket {
    private List<Pair<Integer, Integer>> bucket;

    public Bucket() {
        this.bucket = new LinkedList<>();
    }

    public Integer get(Integer key) {
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key)) {
                return pair.second;
            }
        }
        return -1;
    }

    public void update(Integer key, Integer value) {
        boolean found = false;
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key)) {
                pair.second = value;
                found = true;
            }
        }

        if (!found) {
            this.bucket.add(new Pair<>(key, value));
        }
    }

    public void remove(Integer key) {
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key)) {
                this.bucket.remove(pair);
                break;
            }
        }
    }
}

class MyHashMap {
    private int mod;
    private List<Bucket> buckets;

    public MyHashMap() {
        this.mod = 2069;
        this.buckets = new ArrayList<>();
        for (int i = 0; i < this.mod; ++i) {
            this.buckets.add(new Bucket());
        }
    }

    public void put(int key, int value) {
        int hashKey = key % this.mod;
        this.buckets.get(hashKey).update(key, value);
    }

    public int get(int key) {
        int hashKey = key % this.mod;
        return this.buckets.get(hashKey).get(key);
    }


    public void remove(int key) {
        int hashKey = key % this.mod;
        this.buckets.get(hashKey).remove(key);
    }
}
