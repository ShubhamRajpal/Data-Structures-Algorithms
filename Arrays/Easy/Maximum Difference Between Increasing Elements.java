/*
  Problem Link: https://leetcode.com/problems/maximum-difference-between-increasing-elements/description/
*/

//Approach (Simple iteration)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int maxDiff = -1;

        int maxUptoNow = nums[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < maxUptoNow) {
                maxDiff = Math.max(maxDiff, maxUptoNow - nums[i]);
            }else{
                maxUptoNow = nums[i];
            }
        }

        return maxDiff;
    }
}
