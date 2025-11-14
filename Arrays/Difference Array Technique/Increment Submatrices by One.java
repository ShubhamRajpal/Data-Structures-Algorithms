/*
  Problem Link: https://leetcode.com/problems/increment-submatrices-by-one/description/
*/

//Approach - Using Difference Array Technique similar to 1-D Array
//T.C : O(q*n + n*n)
//S.C : O(n*n) if we consider space of the result 2D Array , else O(1)
class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n][n];

        // Step 1: Process queries (Difference Array Technique)
        for (int[] q : queries) {
            int row1 = q[0];
            int col1 = q[1];
            int row2 = q[2];
            int col2 = q[3];

            for (int i = row1; i <= row2; i++) {
                diff[i][col1] += 1;
                if (col2 + 1 < n) {
                    diff[i][col2 + 1] -= 1;
                }
            }
        }

        // Step 2: Row-wise prefix sums
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        return diff;
    }
}
