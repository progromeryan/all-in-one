package _702;

interface ArrayReader {
    public int get(int index);
}

class Solution {
    public int search(ArrayReader reader, int target) {
        int left = 0;
        int right = 20000;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int num = reader.get(mid);
            if (num == target) return mid;
            if (num > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (reader.get(left) == target) return left;
        if (reader.get(right) == target) return right;

        return -1;
    }
}