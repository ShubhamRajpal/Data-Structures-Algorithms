/*
  Problem Link: https://leetcode.com/problems/minimum-score-triangulation-of-polygon/
*/

//Approach (Recursion + Memoization)
//T.C : O(n*n*n)
//S.C : O(n*n)
class Solution {
    private int[][] dp;

    private int solve(int[] values, int i, int j) {
        // Need at least 3 points to form a triangle
        if (j - i + 1 < 3) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int result = Integer.MAX_VALUE;

        for (int k = i + 1; k < j; k++) {
            int wt = values[i] * values[k] * values[j]
                     + solve(values, i, k)
                     + solve(values, k, j);

            result = Math.min(result, wt);
        }

        dp[i][j] = result;
        return result;
    }

    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        dp = new int[n][n];

        // Initialize dp with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(values, 0, n - 1);
    }
}
