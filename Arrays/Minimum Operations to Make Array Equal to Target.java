/*
  Problem Link: https://leetcode.com/problems/minimum-operations-to-make-array-equal-to-target/description/
  Easier version of this problem: LC 1526 Minimum Number of Increments on Subarrays to Form a Target Array 
*/

//Simple Approach - Keeping track of prev diff
//T.C : O(n)
//S.C : O(1)
class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        int n = nums.length;
        long result = 0;
        int curr = 0;
        int prev = 0;

        for (int i = 0; i < n; i++) {
            curr = target[i] - nums[i];

            // Check for sign change
            if ((curr > 0 && prev < 0) || (curr < 0 && prev > 0)) {
                result += Math.abs(curr);
            } else if (Math.abs(curr) > Math.abs(prev)) {
                result += Math.abs(curr - prev);
            }

            prev = curr;
        }

        return result;
    }
}
