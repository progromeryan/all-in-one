package _670.Dict;

class Solution {
    public int maximumSwap(int num) {
        int[] dict = new int[10];
        char[] arr = String.valueOf(num).toCharArray();

        for (int i = 0; i < arr.length; i++) {
            dict[arr[i] - '0'] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int k = 9; k > arr[i] - '0'; k--) {
                if (dict[k] > i) {
                    char temp = arr[i];
                    arr[i] = arr[dict[k]];
                    arr[dict[k]] = temp;
                    return Integer.parseInt(new String(arr));
                }
            }
        }

        return num;
    }
}