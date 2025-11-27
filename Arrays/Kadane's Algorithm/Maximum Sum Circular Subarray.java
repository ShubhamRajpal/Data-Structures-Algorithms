/*
  Leetcode Link: https://leetcode.com/problems/maximum-sum-circular-subarray/
  GFG Link: https://practice.geeksforgeeks.org/problems/max-circular-subarray-sum-1587115620/1
*/

//Approach-1 (Using Kadane's Algo) - O(N)
T.C: O(n)
T.C: O(1)
class Solution {
    public int kadanes(int[] nums, int flag) {
        int n = nums.length;
        int sum = 0;
        int maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            
            sum = (flag == -1) ? Math.min(nums[i], sum + nums[i]) : Math.max(nums[i], sum + nums[i]);
            
            if (flag == -1 && minSum > sum) {
                minSum = sum;
            } else if (flag == 1 && maxSum < sum) {
                maxSum = sum;
            }
        }

        return (flag == -1) ? minSum : maxSum;
    }

    public int maxSubarraySumCircular(int[] nums) {
        int sum = 0;

        for (int i : nums) {
            sum += i;
        }

        int minSubSum = kadanes(nums, -1);
        int maxSubSum = kadanes(nums, 1);

        int cirSubSum = sum - minSubSum;

        if (maxSubSum > 0) {
            return Math.max(maxSubSum, cirSubSum);
        }

        return maxSubSum;
    }
}
