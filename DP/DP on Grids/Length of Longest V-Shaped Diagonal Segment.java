/*
  Problem Link: https://leetcode.com/problems/length-of-longest-v-shaped-diagonal-segment/
*/

//Approach (Traverse and explore all possible paths + memoization)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    int[] dx = { 1, 1, -1, -1 };
    int[] dy = { 1, -1, -1, 1 };

    public int solve(int i, int j, int d, int canTurn, int[][] grid, int val, int[][][][] dp) {
        int nrow = i + dx[d];
        int ncol = j + dy[d];
        if (nrow < 0 || nrow >= grid.length || ncol < 0 || ncol >= grid[0].length || grid[nrow][ncol] != val) {
            return 0;
        }

        if (dp[i][j][d][canTurn] != -1) {
            return dp[i][j][d][canTurn];
        }

        int curVal = val == 2 ? 0 : 2;
        int maxLen = 0;
        int straight = solve(nrow, ncol, d, canTurn, grid, curVal, dp);
        maxLen = Math.max(maxLen, 1 + straight);
        if (canTurn == 1) {
            int turn = solve(nrow, ncol, (d + 1) % 4, 0, grid, curVal, dp);
            maxLen = Math.max(maxLen, 1 + turn);
        }

        return dp[i][j][d][canTurn] = maxLen;
    }

    public int lenOfVDiagonal(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][][] dp = new int[n][m][4][2];
        for (int[][][] it1 : dp) {
            for (int[][] it2 : it1) {
                for (int it3[] : it2) {
                    Arrays.fill(it3, -1);
                }
            }
        }

        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    for (int d = 0; d < 4; d++) {
                        int canTurn = 1;
                        maxLen = Math.max(maxLen, 1 + solve(i, j, d, canTurn, grid, 2, dp));
                    }
                }
            }
        }

        return maxLen;
    }
}
