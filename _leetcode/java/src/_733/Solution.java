package _733;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int currColor, int newColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] == newColor) return;
        if (image[i][j] != currColor) return;

        image[i][j] = newColor;

        dfs(image, i - 1, j, currColor, newColor);
        dfs(image, i + 1, j, currColor, newColor);
        dfs(image, i, j - 1, currColor, newColor);
        dfs(image, i, j + 1, currColor, newColor);
    }
}