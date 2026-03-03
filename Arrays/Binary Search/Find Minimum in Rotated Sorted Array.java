/*
  Problem Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
*/

// Approach (compare mid with low and high index elements)
// T.C: O(log n)
// S.C: O(1)
class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int lo = 0, hi = n - 1;
        int ans = Integer.MAX_VALUE;
        while (lo <= hi) {

            int mid = (lo + hi) / 2;

            if (nums[lo] <= nums[mid]) {
                ans = Math.min(ans, nums[lo]);
                lo = mid + 1;
            } else if (nums[mid] <= nums[hi]) {
                ans = Math.min(ans, nums[mid]);
                hi = mid - 1;
            }
        }

        return ans;
    }
}
