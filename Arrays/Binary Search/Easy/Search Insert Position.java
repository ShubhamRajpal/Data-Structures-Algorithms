/*
  Problem Link: https://leetcode.com/problems/search-insert-position/
*/


//Approach (using lower bound)
// T.C: O(log N)
// T.C: O(1)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}
