/*
  Problem Link: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
*/

// Approach (Shrink the search space for handling duplicates)
// T.C: O(n) -> worst case O(log n) -> average and best cases
// S.C: O(1)
class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1, ans = Integer.MAX_VALUE;

        while(low <= high){
            int mid = (low+high)/2;

            if(nums[low] == nums[mid] && nums[mid] == nums[high]){
                ans = Math.min(ans, nums[low]);
                low++;
                high--;
                continue;
            }

            if(nums[low] <= nums[mid]){
                ans = Math.min(ans, nums[low]);
                low = mid + 1;
            }else if(nums[mid] <= nums[high]){
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }

            
        }

        return ans;
    }
}
