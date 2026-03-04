/*
  Problem Link: https://leetcode.com/problems/find-peak-element/description/
*/

// Approach (Check for increasing/decreasing curves and eliminates left or right halves accordingly)
// T.C : O(log n)
// S.C: O(1)
class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int low = 1, high = n - 2;

        if (n == 1) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        }

        if (nums[n - 2] < nums[n - 1]) {
            return n - 1;
        }

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            if (nums[mid] > nums[mid - 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
