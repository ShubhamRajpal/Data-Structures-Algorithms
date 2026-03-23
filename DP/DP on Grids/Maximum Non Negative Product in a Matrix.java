/*
  Problem Link: https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/
*/

//Approach - 1 (Recursion + Memoization)
//T.C : O(m*n)
//S.C : O(m*n) + Stack space
class Solution {
    long M = (long) (1e9 + 7);

    public long[] solve(int i, int j, int[][] grid, Map<String, long[]> dp) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return new long[] { grid[i][j], grid[i][j] };
        }

        String s = i +" " + j;
        if (dp.containsKey(s))
            return dp.get(s);

        long minVal = Long.MAX_VALUE, maxVal = Long.MIN_VALUE;
        if (i + 1 < grid.length) {
            long bottom[] = solve(i + 1, j, grid, dp);
            minVal = Math.min(minVal, Math.min(grid[i][j] * bottom[0], grid[i][j] * bottom[1]));
            maxVal = Math.max(maxVal, Math.max(grid[i][j] * bottom[0], grid[i][j] * bottom[1]));
        }

        if (j + 1 < grid[0].length) {
            long right[] = solve(i, j + 1, grid, dp);
            minVal = Math.min(minVal, Math.min(grid[i][j] * right[0], grid[i][j] * right[1]));
            maxVal = Math.max(maxVal, Math.max(grid[i][j] * right[0], grid[i][j] * right[1]));
        }

        long[] ans = new long[] { minVal, maxVal };
        dp.put(s, ans);
        return ans;
    }

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Map<String, long[]> dp = new HashMap<>();
        long[] ans = solve(0, 0, grid, dp);

        return ans[1] < 0 ? -1 : (int) (ans[1] % M);
    }
}

//Approach - 2 (Tabulation - Bottom up)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {
    long M = (long) (1e9 + 7);

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Map<String, long[]> dp = new HashMap<>();

        // Fill the dp table for base case
        String s = (m - 1) + "#" + (n - 1);
        dp.put(s, new long[] { grid[m - 1][n - 1], grid[m - 1][n - 1] });

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1)
                    continue;

                long minVal = Long.MAX_VALUE, maxVal = Long.MIN_VALUE;
                if (i + 1 < m) {
                    String s1 = (i + 1) + "#" + j;
                    long bottom[] = dp.get(s1);
                    minVal = Math.min(minVal, Math.min(grid[i][j] * bottom[0], grid[i][j] * bottom[1]));
                    maxVal = Math.max(maxVal, Math.max(grid[i][j] * bottom[0], grid[i][j] * bottom[1]));
                }

                if (j + 1 < n) {
                    String s2 = i + "#" + (j + 1);
                    long right[] = dp.get(s2);

                    minVal = Math.min(minVal, Math.min(grid[i][j] * right[0], grid[i][j] * right[1]));
                    maxVal = Math.max(maxVal, Math.max(grid[i][j] * right[0], grid[i][j] * right[1]));
                }

                long[] ans = new long[] { minVal, maxVal };
                String temp = i + "#" + j;
                dp.put(temp, ans);
            }
        }

        String ans = 0 + "#" + 0;
        return dp.get(ans)[1] < 0 ? -1 : (int) (dp.get(ans)[1] % M);
    }
}
