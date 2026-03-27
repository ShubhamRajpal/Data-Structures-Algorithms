/*
  Problem Link: https://leetcode.com/problems/equal-sum-grid-partition-i/
*/

//Approach-1 (Using prefix sum)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long totalSum = 0;
        long[][] temp = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                totalSum += grid[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = grid[i][j];
                temp[i][j] += (i > 0) ? temp[i - 1][j] : 0;
                temp[i][j] += (j > 0) ? temp[i][j - 1] : 0;
                temp[i][j] -= (i > 0 && j > 0) ? temp[i - 1][j - 1] : 0;

                if (i == m - 1 && temp[i][j] == totalSum - temp[i][j])
                    return true;
            }

            if (temp[i][n - 1] == totalSum - temp[i][n - 1])
                return true;
        }

        return false;
    }
}


//Approach-2 (Using prefix sum - space optimized)
//T.C : O(m*n)
//S.C : O(m+n)
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[] rowSum = new long[m];
        long[] colSum = new long[n];

        long total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];
            }
        }

        if (total % 2 != 0) {
            return false;
        }

        // Horizontal split
        long upper = 0;
        for (int i = 0; i < m - 1; i++) {
            upper += rowSum[i];
            if (upper == total - upper) {
                return true;
            }
        }

        // Vertical split
        long left = 0;
        for (int j = 0; j < n - 1; j++) {
            left += colSum[j];
            if (left == total - left) {
                return true;
            }
        }

        return false;
    }
}
