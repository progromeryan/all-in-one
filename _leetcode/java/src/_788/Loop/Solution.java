package _788.Loop;

class Solution {
    public int rotatedDigits(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            String num = String.valueOf(i);
            if (num.contains("3") || num.contains("4") || num.contains("7")) continue;
            if (num.contains("2") || num.contains("5") || num.contains("6") || num.contains("9")) res++;
        }

        return res;
    }
}