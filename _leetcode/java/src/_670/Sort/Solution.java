package _670.Sort;

import java.util.Arrays;

class Solution {
    public int maximumSwap(int num) {
        String numStr = String.valueOf(num);
        int len = numStr.length();
        char[] sorted = numStr.toCharArray();
        Arrays.sort(sorted);
        String numStrSorted = new String(sorted);

        for (int i = 0; i < len; i++) {
            char numC = numStr.charAt(i);
            char sortedC = numStrSorted.charAt(len - i - 1);

            if (numC != sortedC) {
                return swap(numStr, i, sortedC);
            }
        }

        return num;
    }

    private int swap(String numStr, int start, char maxNum) {
        char[] numArr = numStr.toCharArray();
        int maxIndex = start;
        for (int i = start; i < numArr.length; i++) {
            if (numArr[i] == maxNum) {
                maxIndex = i;
            }
        }

        numArr[maxIndex] = numArr[start];
        numArr[start] = maxNum;
        return Integer.parseInt(new String(numArr));
    }
}