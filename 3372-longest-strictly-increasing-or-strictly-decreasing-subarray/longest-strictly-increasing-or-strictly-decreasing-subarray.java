class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int maxInc = 1, maxDec = 1;
        int n = nums.length, res = 1;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                maxDec += 1;
                res = Math.max(maxInc, res);
                maxInc = 1;
            } else if (nums[i] < nums[i + 1]) {
                maxInc += 1;
                res = Math.max(maxDec, res);
                maxDec = 1;
            } else {
                res = Math.max(res, Math.max(maxInc,maxDec));
                maxInc = 1;
                maxDec = 1;
            }
        }

        return Math.max(res, Math.max(maxInc,maxDec));
    }
}