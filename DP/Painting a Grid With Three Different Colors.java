/*
  Problem Link: https://leetcode.com/problems/painting-a-grid-with-three-different-colors
  Similar Problem : LC 1411 - https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/
*/

//Approach (Recursion + Memoization)
//T.C : O(n * S * S * m), where S = total states i.e. 3 * 2^m-1
//S.C : O((n * S) + (S * m)) where n * S is because of memoization array t, and S * m is for storing columnStates
class Solution {
    List<String> colStates = new ArrayList<>();
    int mod = (int) 1e9 + 7;
    char[] colorArr = { 'R', 'G', 'B' };

    public void generateStates(int idx, char prevColor, String sb, int rows) {
        if (idx == rows) {
            colStates.add(sb);
            return;
        }

        for (char ch : colorArr) {
            if (ch == prevColor) {
                continue;
            }
            generateStates(idx + 1, ch, sb + ch, rows);
        }
    }

    public int solve(int prevColIdx, int remainingCols, int m, int[][] dp) {
        if (remainingCols == 0) {
            return 1;
        }

        if (dp[prevColIdx][remainingCols] != -1)
            return dp[prevColIdx][remainingCols];

        String prevCol = colStates.get(prevColIdx);

        int ways = 0;
        for (int i = 0; i < colStates.size(); i++) {
            if (i == prevColIdx)
                continue;

            String curCol = colStates.get(i);
            boolean valid = true;
            for (int j = 0; j < m; j++) {
                if (curCol.charAt(j) == prevCol.charAt(j)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ways = (ways + solve(i, remainingCols - 1, m, dp)) % mod;
            }
        }

        return dp[prevColIdx][remainingCols] = ways;
    }

    public int colorTheGrid(int m, int n) {
        generateStates(0, '#', "", m);
        int res = 0;

        int[][] dp = new int[colStates.size()][n + 1];
        for (int[] arrA : dp) {
            Arrays.fill(arrA, -1);

        }
        for (int i = 0; i < colStates.size(); i++) {
            res = (res + solve(i, n - 1, m, dp)) % mod;
        }

        return res;
    }
}
