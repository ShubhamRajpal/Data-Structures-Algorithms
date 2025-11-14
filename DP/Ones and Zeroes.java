/*
  Problem Link: https://leetcode.com/problems/ones-and-zeroes/
*/

// Approach-1 (Recursion and Memoization)
// T.C : O(L * m * n)
//       where L = number of strings, m = max number of 0's allowed, n = max number of 1's allowed
//       For each index (L), we compute at most m*n states once due to memoization.
// S.C : O(L * m * n) for the DP cache + O(L) recursion stack

class Solution {
    int maxSubsetCnt = 0;
    private int[] countOnesZeros(String s){
        int cnt0 = 0, cnt1 = 0;
        for(char ch : s.toCharArray()){
            cnt0 += ch == '0' ? 1 : 0;
            cnt1 += ch == '1' ? 1 : 0;
        }

        return new int[]{cnt0,cnt1};
    }

    private int solve(int idx, int m, int n, int[][][] dp, String[] strs){
        if(idx >= strs.length || (m == 0 && n == 0)){
            return 0;
        }

        if(dp[idx][m][n] != -1)  return dp[idx][m][n];

        int[] cnt = countOnesZeros(strs[idx]);
        int take = 0;
        if(cnt[0] <= m && cnt[1] <= n){

            take = 1 + solve(idx+1, m-cnt[0], n-cnt[1], dp, strs);
        }
        int notTake = solve(idx+1, m, n, dp, strs);

        return dp[idx][m][n] = Math.max(take, notTake);
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l+1][m+1][n+1];
        for(int[][] it : dp){
            for(int[] it1 : it){
                Arrays.fill(it1, -1);
            }
        }
        return solve(0, m, n, dp, strs);
       
    }
}


// Approach-2 (DP-Bottom Up)
// T.C : O(L * m * n)
//       For each string, we iterate over m * n states once.
// S.C : O(L * m * n)
//       We only use a 3D DP array, no recursion

class Solution {
    private int[] countOnesZeros(String s) {
        int cnt0 = 0, cnt1 = 0;
        for (char ch : s.toCharArray()) {
            cnt0 += ch == '0' ? 1 : 0;
            cnt1 += ch == '1' ? 1 : 0;
        }

        return new int[] { cnt0, cnt1 };
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;

        int[][][] dp = new int[l + 1][m + 1][n + 1];
        for (int idx = l - 1; idx >= 0; idx--) {
            int[] cnt = countOnesZeros(strs[idx]);
            for (int j = m; j >= 0; j--) {
                for (int i = n; i >= 0; i--) {
                    int take = 0, notTake = 0;
                    if (j  >= cnt[0] && i >= cnt[1]) {
                        take = 1 + dp[idx + 1][j - cnt[0]][i - cnt[1]];
                    }
                    notTake = dp[idx + 1][j][i];
                    dp[idx][j][i] = Math.max(take, notTake);
                }
            }
        }
        return dp[0][m][n];
    }
}
