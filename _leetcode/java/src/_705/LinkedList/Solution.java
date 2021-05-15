package _705.LinkedList;

import java.util.*;

class MyHashSet {
    private Bucket[] buckets;
    private int mod;

    public MyHashSet() {
        // 通常选择prime number作为modulo
        this.mod = 769;
        this.buckets = new Bucket[this.mod];
        for (int i = 0; i < this.mod; ++i) {
            this.buckets[i] = new Bucket();
        }
    }

    protected int getHash(int key) {
        return (key % this.mod);
    }

    public void add(int key) {
        int bucketIndex = this.getHash(key);
        this.buckets[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = this.getHash(key);
        this.buckets[bucketIndex].delete(key);
    }

    public boolean contains(int key) {
        int bucketIndex = this.getHash(key);
        return this.buckets[bucketIndex].exists(key);
    }
}


class Bucket {
    // 当位置确定的时,LinkedList 插入和删除都是O(1)时间
    private LinkedList<Integer> list;

    public Bucket() {
        list = new LinkedList<>();
    }

    public void insert(Integer key) {
        int index = this.list.indexOf(key);
        if (index == -1) {
            this.list.addFirst(key);
        }
    }

    public void delete(Integer key) {
        this.list.remove(key);
    }

    public boolean exists(Integer key) {
        int index = this.list.indexOf(key);
        return (index != -1);
    }
}
