package _658.BinarySearch;

import java.util.*;

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;

        int index = -1;

        while (left + 1 < right){
            int mid = (left + right) / 2;
            if (arr[mid] == x) {
                return helper(arr, mid, k, x);
            }

            if (arr[mid] > x) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
            return helper(arr, left, k, x);
        }

        return helper(arr, right, k, x);
    }

    private List<Integer> helper(int[] arr, int index, int k, int x) {
        List<Integer> res = new ArrayList<>();
        res.add(arr[index]);
        k--;
        int left = index -1;
        int right = index + 1;

        while (k != 0) {
            int leftDiff = Integer.MAX_VALUE;
            int rightDiff = Integer.MAX_VALUE;

            if (left >= 0) {
                leftDiff = Math.abs(x - arr[left]);
            }

            if (right <= arr.length - 1) {
                rightDiff = Math.abs(x - arr[right]);
            }

            if (leftDiff <= rightDiff) {
                res.add(0, arr[left]);
                left--;
            } else{
                res.add(arr[right]);
                right++;
            }
            k--;
        }

        return res;
    }
}