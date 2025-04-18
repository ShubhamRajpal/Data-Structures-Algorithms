/*
  T.C. - O(N)
  S.C. - O(1)
  Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int j = 0;
        for(int i = 1;i < n; i++){
            if(nums[i] != nums[i-1]){
                j++;
                nums[j] = nums[i];
            }
        }

        return j+1;
    }
}
