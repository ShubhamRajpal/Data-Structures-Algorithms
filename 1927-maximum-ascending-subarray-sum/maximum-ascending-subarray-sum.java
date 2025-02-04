class Solution {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int res = nums[0], sum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                sum += nums[i];
                res = Math.max(res, sum);
            } else {
                sum = nums[i];
            }
        }

        return res;
    }
}