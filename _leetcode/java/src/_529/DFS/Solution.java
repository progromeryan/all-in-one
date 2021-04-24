package _529.DFS;

class Solution {
    int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        char curr = board[x][y];

        if (curr == 'M') {
            board[x][y] = 'X';
            return board;
        }

        if (curr == 'E') {
            int count = find(board, x, y);
            if (count == 0) {
                reveal(board, x, y);
            } else {
                board[x][y] = (char) (count + '0');
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

    private void reveal(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        if (board[i][j] == 'M' || board[i][j] == 'B') return;
        int count = find(board, i, j);
        if (count != 0) {
            board[i][j] = (char) (count + '0');
            return;
        }
        board[i][j] = 'B';

        for (int[] dir : dirs) {
            reveal(board, i + dir[0], j + dir[1]);
        }
    }
}

