package _845.Approach2;

class Solution {
    public int longestMountain(int[] arr) {
        int N = arr.length;
        int ans = 0, base = 0;
        while (base < N) {
            int end = base;
            // if base is a left-boundary
            if (end + 1 < N && arr[end] < arr[end + 1]) {
                // set end to the peak of this potential mountain
                while (end + 1 < N && arr[end] < arr[end + 1]) end++;

                // if end is really a peak..
                if (end + 1 < N && arr[end] > arr[end + 1]) {
                    // set end to the right-boundary of mountain
                    while (end + 1 < N && arr[end] > arr[end + 1]) end++;
                    // record candidate answer
                    ans = Math.max(ans, end - base + 1);
                }
            }

            base = Math.max(end, base + 1);
        }

        return ans;
    }
}
