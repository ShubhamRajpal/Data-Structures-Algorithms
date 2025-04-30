/*
  Problem Link: https://leetcode.com/problems/maximum-subarray/description/
*/

//T.C- O(N)
//S.C- O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxi) {
                maxi = (int) sum;
            }

            if (sum < 0)
                sum = 0;

        }

        return maxi;
    }
}
