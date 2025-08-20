/*
  Problem Link: https://leetcode.com/problems/maximal-square/description/
*/

//Approach - Using Simple Bottom Up
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        int side = matrix[0][0] == '0' ? 0 : 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] == '0' ? 0 : 1;
                } else {
                    if (matrix[i][j] == '1') {
                        dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    }
                }
                side = Math.max(side, dp[i][j]);
            }
        }
        
        return side * side;
    }
}
