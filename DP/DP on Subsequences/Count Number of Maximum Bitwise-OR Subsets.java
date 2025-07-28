/*
  Problem Link: https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/
*/

//Approach-1 (Using simple recursion to find subsets)
//T.C : O(2^n)
//S.C : O(1)
class Solution {

    public int solve(int ind, int curOR, int maxOR, int[] nums){
        if(ind == nums.length){
            return curOR == maxOR ? 1 : 0;
        }

        int notTake = solve(ind+1, curOR, maxOR, nums);
        int take = solve(ind+1, curOR | nums[ind] , maxOR, nums);

        return take+notTake;


    }
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int maxOR = 0;
        for(int i : nums){
            maxOR |= i;
        }

        return solve(0, 0,  maxOR, nums);
    }
}


//Approach-2 (Memoizing to store subproblems result)
//T.C : O(n*maxOr)
//S.C : O(1)
class Solution {

    public int solve(int ind, int curOR, int maxOR, int[] nums, int[][] dp){
        if(ind == nums.length){
            return curOR == maxOR ? 1 : 0;
        }

        if(dp[ind][curOR] != -1)  return dp[ind][curOR];

        int notTake = solve(ind+1, curOR, maxOR, nums, dp);
        int take = solve(ind+1, curOR | nums[ind] , maxOR, nums, dp);

        return dp[ind][curOR] = take+notTake;

    }
    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int maxOR = 0;
        for(int i : nums){
            maxOR |= i;
        }

        int[][] dp = new int[n][maxOR+1];
        for(int it[] : dp) Arrays.fill(it, -1);

        return solve(0, 0,  maxOR, nums, dp);
    }
}

//Approach-3 (Tabulation)
//T.C : O(n*maxOr)
//S.C : O(1)
class Solution {

    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int maxOR = 0;
        for (int i : nums) {
            maxOR |= i;
        }

        int[][] dp = new int[n+1][maxOR + 1];
        dp[n][maxOR] = 1;
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int curOR = maxOR; curOR >= 0; curOR--) {
                int notTake = dp[ind + 1][curOR];
                int take = 0;
                if((curOR | nums[ind]) <= maxOR) take = dp[ind + 1][curOR | nums[ind]];
                dp[ind][curOR] = take + notTake;
            }
        }

        return dp[0][0];
    }
}
