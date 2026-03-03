/*
  Problem Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/  
*/

// Approach (using lower bound and upper bound concept)
// T.C: O(log n)
// S.C: O(1)
class Solution {

    public int findLB(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;
        int ans = -1;

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

    public int findUB(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] > target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;

        int lb = findLB(nums, target);
        int ub = findUB(nums, target);

        if (lb == -1 || nums[lb] != target) {
            return new int[] { -1, -1 };
        }

        return new int[] { lb, ub - 1 };

    }
}
