package _729.TreeMap1;

import java.util.*;

class MyCalendar {
    TreeMap<Integer, Integer> map;

    public MyCalendar() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        if (map.containsKey(start)) return false;

        Integer prev = map.lowerKey(start);
        if (prev != null) {
            if (start < map.get(prev)) return false;
        }

        Integer next = map.higherKey(start);
        if (next != null) {
            if (end > next) return false;
        }

        if (prev != null && start == map.get(prev) && next != null && end == next) {
            int nextEnd = map.get(next);
            map.remove(next);
            map.put(prev, nextEnd);
        } else if (prev != null && start == map.get(prev)) {
            map.put(prev, end);
        } else if (next != null && end == next) {
            int nextEnd = map.get(next);
            map.put(start, nextEnd);
            map.remove(next);
        } else {
            map.put(start, end);
        }

        return true;
    }
}
