class Solution {

    private int solveRecursive(int i, int j, int k, String s) {
        if (i > j)
            return 0;
        if (i == j)
            return 1;

        int res = 0;
        if (s.charAt(i) == s.charAt(j)) {
            res = 2 + solveRecursive(i + 1, j - 1, k, s);
        } else {
            res = Math.max(solveRecursive(i + 1, j, k, s), solveRecursive(i, j - 1, k, s));
            int cost = Math.min(Math.abs(s.charAt(i) - s.charAt(j)), 26 - Math.abs(s.charAt(i) - s.charAt(j)));
            if (k >= cost) {
                res = Math.max(res, 2 + solveRecursive(i + 1, j - 1, k - cost, s));
            }
        }

        return res;
    }

    private int solveMemoize(int i, int j, int k, String s, int[][][] dp) {
        if (i > j)
            return 0;
        if (i == j)
            return 1;

            if(dp[i][j][k] != -1) return dp[i][j][k];

        int res = 0;
        if (s.charAt(i) == s.charAt(j)) {
            res = 2 + solveMemoize(i + 1, j - 1, k, s, dp);
        } else {
            res = Math.max(solveMemoize(i + 1, j, k, s, dp), solveMemoize(i, j - 1, k, s, dp));
            int cost = Math.min(Math.abs(s.charAt(i) - s.charAt(j)), 26 - Math.abs(s.charAt(i) - s.charAt(j)));
            if (k >= cost) {
                res = Math.max(res, 2 + solveMemoize(i + 1, j - 1, k - cost, s, dp));
            }
        }

        return dp[i][j][k] = res;
    }

    public int longestPalindromicSubsequence(String s, int k) {

        int n = s.length();
        // return solveRecursive(0, n - 1, k, s);

        // Memoiation
        int[][][] dp = new int[n][n][k + 1];
        for(int it[][] : dp){
            for(int it1[] : it) Arrays.fill(it1,-1);
        }
        return solveMemoize(0, n - 1, k, s, dp);
    }
}