package _717;

class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        for (int i = 0; i < bits.length - 1; i++) {
            if (bits[i] == 0) continue;
            if (i + 1 == bits.length - 1) return false;
            i++;
        }

        return true;
    }
}