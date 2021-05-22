package _845.Approach1;


class Solution {
    public int longestMountain(int[] arr) {
        int maxLen = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int num = arr[i];
            if (num > arr[i - 1] && num > arr[i + 1]) {
                int len = helper(arr, i);
                maxLen = Math.max(maxLen, len);
            }
        }

        return maxLen;
    }

    private int helper(int[] arr, int index) {
        int len = 1;
        int left = index - 1;
        int right = index + 1;

        while (left >= 0 || right < arr.length) {
            if (left >= 0 && arr[left] < arr[left + 1]) {
                len++;
                left--;
            } else if (right < arr.length && arr[right] < arr[right - 1]) {
                len++;
                right++;
            } else {
                break;
            }
        }
        return len;
    }
}