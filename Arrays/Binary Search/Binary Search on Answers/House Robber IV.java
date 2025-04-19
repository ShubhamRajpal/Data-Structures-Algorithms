/*
  Problem Link: https://leetcode.com/problems/house-robber-iv/?envType=daily-question&envId=2025-03-15
*/

//Approach-1 (Brute Force - Recursion + Memoization)
//T.C : O(n*k)
//S.C : O(n*k)
class Solution {

    private int solveMemoized(int ind, int cnt, int[] nums, int k, int[][] dp) {

        if (cnt >= k) {
            return 0;
        }

        if (ind >= nums.length) {
            return (int) (1e9);
        }

        if (dp[ind][cnt] != -1) {
            return dp[ind][cnt];
        }

        int take = Math.max(nums[ind], solveMemoized(ind + 2, cnt + 1, nums, k, dp));
        int notTake = solveMemoized(ind + 1, cnt, nums, k, dp);

        return dp[ind][cnt] = Math.min(take, notTake);
    }

    public int minCapability(int[] nums, int k) {

        int n = nums.length;

        // Approach 1 (Brute -> Recursion + Memoization)
        int[][] dp = new int[n][k + 1];
        for (int it[] : dp) {
            for (int j = 0; j < it.length; j++) {
                it[j] = -1;
            }
        }

        // return solveMemoized(0, 0, nums, k, dp);

        

    }
}


//Approach-2 (Binary Search on Answer "Minimize the Maximum Robbed Amount(capability)")
//T.C : O(n*log(maxC))
//S.C : O(1)
class Solution {

    private boolean isRobberyPossible(int capabilityAllowed, int[] nums, int k) {

        int houseCnt = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= capabilityAllowed) {
                houseCnt++;
                i++;
            }
        }

        return houseCnt >= k;
    }

    public int minCapability(int[] nums, int k) {

        int n = nums.length;
        int l = Arrays.stream(nums).min().getAsInt();
        int r = Arrays.stream(nums).max().getAsInt();

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (isRobberyPossible(mid, nums, k)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return l;
    
    }
}
