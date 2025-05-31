/*
  Problem Link: https://leetcode.com/problems/snakes-and-ladders/
*/

//T.C : O(n^2), The maximum number of cells is n^2 and each cell is visited at most once.
//S.C : O(n^2)
class Solution {
    private int[] getCoordinate(int num, int n) {
        int rowTop = (num - 1) / n;
        int rowBottom = (n - 1) - rowTop;
        int col = (num - 1) % n;

        if ((n % 2 == 1 && rowBottom % 2 == 1) || (n % 2 == 0 && rowBottom % 2 == 0)) {
            col = (n - 1) - col;
        }

        return new int[] { rowBottom, col };

    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        q.add(1);
        visited[n - 1][0] = true;
        int steps = 0;

        while (!q.isEmpty()) {

            int N = q.size();

            while (N != 0) {

                int num = q.poll();
                if (num == (n * n))
                    return steps;

                for (int i = 1; i <= 6; i++) {
                    int val = num + i;

                    if (val > n * n)
                        break;

                    int[] rowCol = getCoordinate(val, n);

                    int row = rowCol[0];
                    int col = rowCol[1];

                    if (visited[row][col])
                        continue;

                    visited[row][col] = true;

                    if (board[row][col] != -1) {
                        q.add(board[row][col]);
                    } else {
                        q.add(val);
                    }
                }
                N--;

            }

            steps++;

        }

        return -1;
    }
}
