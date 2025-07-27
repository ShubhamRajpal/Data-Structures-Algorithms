/*
  Problem Link: https://leetcode.com/problems/count-hills-and-valleys-in-an-array/
*/

// Approach (Do what is asked)
// T.C: O(n)
// S.C: O(1)

class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int prevIdx = 0;
        for (int i = 1; i < n - 1; i++) {

            if (nums[i] == nums[i + 1]) continue;

            if (nums[i] < nums[prevIdx] && nums[i] < nums[i + 1] || nums[prevIdx] < nums[i] && nums[i] > nums[i + 1]) {
                cnt++;
            }

            prevIdx = i;

        }

        return cnt;
    }
}
