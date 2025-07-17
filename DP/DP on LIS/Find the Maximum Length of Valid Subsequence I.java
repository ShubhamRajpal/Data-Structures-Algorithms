
//Approach - 1 - Using LIS Pattern (Recursion + Memoization) - Memoization will give MLE due to high constraints
//T.C : Without Memoization O(2^n), With memoization - O(n^2) but MLE
//S.C : O(n^2)
class Solution {
    private int solve(int curIdx, int prevIdx, int rem, int[] nums, int[][] dp) {
        if (curIdx >= nums.length)
            return 0;

        if (dp[curIdx][prevIdx + 1] != -1)
            return dp[curIdx][prevIdx + 1];

        int notTake = solve(curIdx + 1, prevIdx, rem, nums, dp);
        int take = 0;
        if (prevIdx == -1 || (nums[prevIdx] + nums[curIdx]) % 2 == rem) {
            take = 1 + solve(curIdx + 1, curIdx, rem, nums, dp);
        }

        return dp[curIdx][prevIdx + 1] = Math.max(take, notTake);
    }

    public int maximumLength(int[] nums) {
        int maxSubLength = 0;
        int n = nums.length;
        
        // Case1: for rem == 0
        int[][] dp = new int[n + 1][n + 1];
        for (int[] it : dp) Arrays.fill(it, -1);
        maxSubLength = Math.max(maxSubLength, solve(0, -1, 0, nums, dp));
        
        // Case2: for rem == 1
        for (int[] it : dp) Arrays.fill(it, -1);
        maxSubLength = Math.max(maxSubLength, solve(0, -1, 1, nums, dp));

        return maxSubLength;

    }
}

//Approach - 2 - Using LIS Pattern (Bottom Up) - TLE
//T.C : O(n^2) but TLE
//S.C : O(n)
class Solution {

    public int maximumLength(int[] nums) {
        int maxSubLength = 0;
        int n = nums.length;
        
        int[][] dp = new int[2][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int mod = (nums[i] + nums[j]) % 2;
                dp[mod][i] = Math.max(dp[mod][i], 1 + dp[mod][j]);
                maxSubLength = Math.max(maxSubLength, dp[mod][i]);

            }
        }

        return maxSubLength + 1;
    }
}
