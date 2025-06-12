/*
   Problem Link: https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array 
*/

//Approach - simple iteration
//T.C : O(n) 
//S.C : O(1)
class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int maxi = Math.abs(nums[0] - nums[n-1]);

        for(int i = 0; i < n-1; i++){
            int val = Math.abs(nums[i] - nums[i+1]);
            maxi = Math.max(maxi, val);
        }

        return maxi;
    }
}
