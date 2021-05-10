package _681;

import java.util.*;


class Solution {
    public String nextClosestTime(String time) {
        HashSet<Integer> valid = new HashSet<>();
        for (char c : time.toCharArray()) {
            if (Character.isDigit(c)) {
                valid.add(c - '0');
            }
        }

        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);
        int base = hour * 60 + minute;

        int close = Integer.MAX_VALUE;
        String res = "";

        int min = Integer.MAX_VALUE;
        String minRes = "";

        for (int h = 0; h <= 23; h++) {
            for (int m = 0; m <= 59; m++) {
                int num1 = h / 10;
                int num2 = h % 10;
                int num3 = m / 10;
                int num4 = m % 10;
                if (!valid.contains(num1)) continue;
                if (!valid.contains(num2)) continue;
                if (!valid.contains(num3)) continue;
                if (!valid.contains(num4)) continue;

                int diff = h * 60 + m - base;
                if (diff < close && diff > 0) {
                    close = diff;
                    res = "" + num1 + num2 + ":" + num3 + num4;
                }

                if (h * 60 + m < min) {
                    min = h * 60 + m;
                    minRes = "" + num1 + num2 + ":" + num3 + num4;
                }
            }
        }

        return res.length() == 0 ? minRes : res;
    }
}