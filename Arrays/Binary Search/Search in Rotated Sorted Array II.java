/*
  Problem Link: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
*/

// Approach (same as part 1. For handling duplicates -> just shrinkg the search space)
// T.C: O(n) -> worst case O(log n) -> average cases
// S.C: O(1)
class Solution {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target)
                return true;

            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
            }

            //Left half is sorted
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Right half is sorted
            else if (nums[mid] <= nums[high]) {
                if (target >= nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }
}
