/*
  Problem Link: https://leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k
*/

//Approach-1 (Using Recursion+Memoization)
//T.C : O(2^n), take and skip
//S.C : O(n^2) auxiliary space, and O(n) of recursion stack
class Solution {

    private int solve(int ind, String s, int curVal, int pow, int k, int[][] dp) {

        if (ind < 0) {
            return 0;
        }

        if (dp[ind][pow] != -1) {
            return dp[ind][pow];
        }

        int notTake = solve(ind - 1, s, curVal, pow, k, dp);
        int take = 0;
        if (curVal + (s.charAt(ind) -'0') * Math.pow(2, pow) <= k) {
            take = 1 + solve(ind - 1, s, curVal + (s.charAt(ind)-'0') * (int)Math.pow(2, pow), pow + 1, k, dp);
        }

        return dp[ind][pow] = Math.max(take, notTake);
    }

    public int longestSubsequence(String s, int k) {
        int n = s.length();

        int[][] dp = new int[n][n];
        for(int it[] : dp) Arrays.fill(it, -1);
        return solve(n - 1, s, 0, 0, k, dp);
    }
}

//Approach-2 (Using Greedy)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int longestSubsequence(String s, int k) {
        int length = 0;
        long powerValue = 1;

        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                length++;
            } else if (powerValue <= k) {
                length++;
                k -= powerValue;
            }

            if (powerValue <= k) {
                powerValue <<= 1; // powerValue *= 2
            }
        }

        return length;
    }
}
