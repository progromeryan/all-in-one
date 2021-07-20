package _890;

import java.util.*;

import structures.*;

class Solution {
    class Node {
        int sum;
        int start;

        public Node(int sum, int start) {
            this.sum = sum;
            this.start = start;
        }
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {

        int total1 = getMax(nums, firstLen, secondLen);
        int total2 = getMax(nums, secondLen, firstLen);
        return Math.max(total1, total2);
    }

    public int getMax(int[] nums, int firstLen, int secondLen) {
        int maxSum = 0;
        int currSum = 0;
        for (int i = 0; i < nums.length - firstLen; i++) {
            currSum += nums[i];
            if (i >= firstLen) {
                currSum -= nums[i - firstLen];
            }

            if (i >= firstLen - 1) {
                Node sec1 = helper(nums, 0, i - firstLen, secondLen);
                Node sec2 = helper(nums, i + 1, nums.length - 1, secondLen);
                maxSum = Math.max(maxSum, currSum + Math.max(sec1.sum, sec2.sum));
            }
        }
        return maxSum;
    }

    public Node helper(int[] nums, int start, int end, int len) {
        int currSum = 0;
        int maxSum = 0;
        int maxIndex = 0;

        for (int i = start; i <= end; i++) {
            currSum += nums[i];
            if (i - start >= len) {
                currSum -= nums[start + i - len];
            }

            if (i - start >= len - 1 && currSum > maxSum) {
                maxSum = currSum;
                maxIndex = i - len + 1;
            }
        }

        return new Node(maxSum, maxIndex);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{8, 20, 6, 2, 20, 17, 6, 3, 20, 8, 12};
        solution.maxSumTwoNoOverlap(nums, 5, 4);
    }
}