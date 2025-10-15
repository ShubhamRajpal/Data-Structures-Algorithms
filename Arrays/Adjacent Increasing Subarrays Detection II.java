/*
  Problem Link: https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/
*/

//Approach (Using same concept as we did for Adjacent Increasing Subarrays Detection I)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int inc1 = 0, inc2 = 1, maxLen = 1;

        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                inc2++;
            } else {
                inc1 = inc2;
                inc2 = 1;
            }
            maxLen = Math.max(maxLen, Math.max(inc2 / 2, Math.min(inc1, inc2)));
        }

        return maxLen;
    }
}
