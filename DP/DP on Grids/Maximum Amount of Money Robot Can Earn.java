/*
  Problem Link: https://leetcode.com/problems/maximum-amount-of-money-robot-can-earn/description/
  Note: Add bottom-up code as well
*/

//Approach (Recursion + Memoization)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    int m, n;
    int[][][] dp;

    int solve(int[][] coins, int i, int j, int neu) {

        // Out of bounds
        if (i >= m || j >= n) return Integer.MIN_VALUE;

        // Base case
        if (i == m - 1 && j == n - 1) {
            if (coins[i][j] < 0 && neu > 0) return 0;
            return coins[i][j];
        }

        // Memo
        if (dp[i][j][neu] != Integer.MIN_VALUE) {
            return dp[i][j][neu];
        }

        int best = Integer.MIN_VALUE;

        // Move Down
        int down = solve(coins, i + 1, j, neu);
        if (down != Integer.MIN_VALUE) {
            best = Math.max(best, coins[i][j] + down);
        }

        // Move Right
        int right = solve(coins, i, j + 1, neu);
        if (right != Integer.MIN_VALUE) {
            best = Math.max(best, coins[i][j] + right);
        }

        // Neutralize (skip negative)
        if (coins[i][j] < 0 && neu > 0) {
            int downSkip = solve(coins, i + 1, j, neu - 1);
            int rightSkip = solve(coins, i, j + 1, neu - 1);

            int skipBest = Math.max(downSkip, rightSkip);
            if (skipBest != Integer.MIN_VALUE) {
                best = Math.max(best, skipBest);
            }
        }

        return dp[i][j][neu] = best;
    }

    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;

        dp = new int[m][n][3];

        // Initialize with MIN_VALUE
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }

        return solve(coins, 0, 0, 2);
    }
}
