package _775.MergeSort;

class Solution {
    int[] temp;

    public boolean isIdealPermutation(int[] nums) {
        int size = nums.length;
        temp = new int[size];
        int local = 0;
        for (int i = 1; i < size; i++) {
            if (nums[i] < nums[i - 1]) local++;
        }

        int global = mergeSort(nums, 0, size - 1);
        return global == local;
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;

        int middle = left + (right - left) / 2;
        int inv = mergeSort(nums, left, middle) + mergeSort(nums, middle + 1, right);

        // 左边和右边数组的开始
        int i = left;
        int j = middle + 1;
        int k = 0;

        while (i <= middle && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
                inv += middle - i + 1;
            }
        }

        while (i <= middle) temp[k++] = nums[i++];
        while (j <= right) temp[k++] = nums[j++];

        for (int m = 0; m < k; m++) {
            nums[left + m] = temp[m];
        }

        return inv;
    }
}