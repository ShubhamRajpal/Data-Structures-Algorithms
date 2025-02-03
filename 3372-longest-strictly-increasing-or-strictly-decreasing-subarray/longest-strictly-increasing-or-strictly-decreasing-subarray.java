class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int maxInc = 1, maxDec = 1;
        int n = nums.length, res = 1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                maxDec += 1;
                maxInc = 1;
                res = Math.max(maxDec, res);
            } else if (nums[i] < nums[i + 1]) {
                maxInc += 1;
                maxDec = 1;
                res = Math.max(maxInc, res);
            } else {
                maxInc = 1;
                maxDec = 1;
            }
        }

        return res;
    }
}