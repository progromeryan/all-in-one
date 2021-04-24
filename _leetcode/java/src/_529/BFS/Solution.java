package _529.BFS;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];

        if (board[x][y] == 'B') {
            return board;
        }

        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        int count = find(board, x, y);
        if (count != 0) {
            board[x][y] = (char) (count + '0');
            return board;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            board[curr[0]][curr[1]] = 'B';

            for (int[] dir : dirs) {
                int i = curr[0] + dir[0];
                int j = curr[1] + dir[1];

                if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) continue;
                if (board[i][j] != 'E') continue;
                int currCount = find(board, i, j);
                if (currCount != 0) {
                    board[i][j] = (char) (currCount + '0');
                    continue;
                }
                // 在当前层赋值可以减少迭代次数
                board[i][j] = 'B';
                queue.offer(new int[]{i, j});
            }
        }

        return board;
    }

    private int find(char[][] board, int i, int j) {
        int count = 0;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                if (board[x][y] == 'M' || board[x][y] == 'X') {
                    count++;
                }
            }
        }

        return count;
    }
}
