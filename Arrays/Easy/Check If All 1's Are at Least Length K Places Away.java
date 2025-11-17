/*
  Problem Link: https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/
*/

//Approach - Maintain two pointers to count 0s between
//T.C : O(n)
//S.C : O(1)
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;
        int prevOnePos = -1;

        for(int i = 0; i < n; i++){
            if(nums[i] == 0) continue;

            if(prevOnePos > -1 && i - prevOnePos - 1 < k) return false;
            prevOnePos = i; 
        }

        return true;

    }
}
