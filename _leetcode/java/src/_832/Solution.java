package _832;


class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            swap(image, i);
        }

        return image;
    }

    private void swap(int[][] image, int row) {
        int left = 0;
        int right = image[row].length - 1;
        while (left <= right) {
            int temp = image[row][left];
            image[row][left] = image[row][right];
            image[row][right] = temp;

            if (left == right) {
                image[row][left] ^= 1;
            } else {
                image[row][left] ^= 1;
                image[row][right] ^= 1;
            }

            left++;
            right--;
        }
    }
}