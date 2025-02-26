class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int maxAbsSum = Integer.MIN_VALUE;
        int curSubarraySum = nums[0], maxSubarraySum = nums[0];
        for (int i = 1; i < n; i++) {
            curSubarraySum = Math.max(nums[i], curSubarraySum + nums[i]);
            maxSubarraySum = Math.max(maxSubarraySum, curSubarraySum);
        }

        curSubarraySum = nums[0];
        int minSubarraySum = nums[0];
        for (int i = 1; i < n; i++) {
            curSubarraySum = Math.min(nums[i], curSubarraySum + nums[i]);
            minSubarraySum = Math.min(minSubarraySum, curSubarraySum);
        }

        return Math.max(Math.abs(minSubarraySum), Math.abs(maxSubarraySum));
    }
}