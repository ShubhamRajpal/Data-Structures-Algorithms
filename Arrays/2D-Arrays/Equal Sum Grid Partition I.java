/*
  Problem Link: https://leetcode.com/problems/equal-sum-grid-partition-i/description/
*/

// Approach-1 (using prefix Sum)
// T.C: O(m * n)
// S.C: O(m * n)
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

// Approach-2 (Optimal : using 1-d array for storing rowsum and colsum)
// T.C: O(m * n)
// S.C: O(m + n)
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        long[] rowSum = new long[m];
        long[] colSum = new long[n];
        long total = 0;

        // Compute sums
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSum[i] += grid[i][j];
                colSum[j] += grid[i][j];
                total += grid[i][j];
            }
        }

        if (total % 2 != 0) return false;

        if (check(rowSum, total)) return true;
        if (check(colSum, total)) return true;

        return false;
    }

    private boolean check(long[] arr, long total) {
        long left = arr[0];
        long right = total - left;

        for (int i = 1; i < arr.length; i++) {
            if (left == right) return true;
            else if (left > right) return false;
            left += arr[i];
            right -= arr[i];
        }

        return false;
    }
}
