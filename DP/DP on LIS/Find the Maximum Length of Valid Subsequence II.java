/*
  Problem Link: https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/
*/
//Approach - Using LIS Pattern (Bottom Up)
//T.C : O(n^2)
//S.C : O(n*k)
class Solution {
    public int maximumLength(int[] nums, int k) {
        int[][] dp = new int[k][n];
        for (int i[] : dp) {
            Arrays.fill(i, -1);
        }

        int maxSub = 1;

        //mod = 0 to k-1
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                int mod = (nums[j] + nums[i]) % k;
                dp[mod][i] = Math.max(dp[mod][i], 1 + dp[mod][j]);
                maxSub = Math.max(maxSub, dp[mod][i]);
            }
        }

        return maxSub;
    }
}
