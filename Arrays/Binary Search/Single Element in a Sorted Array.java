/*
  Problem Link: https://leetcode.com/problems/single-element-in-a-sorted-array/
*/

// Approach-1 (Using Binary Search) 
// T.C: O(log(n))
// S.C: O(1)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return nums[0];
        }

        if(nums[0] != nums[1]){
            return nums[0];
        }

        if(nums[n-2] != nums[n-1]){
            return nums[n-1];
        }

        int lo = 0, hi = n - 1;
        while(lo <= hi){

            int mid = (lo + hi) / 2;

            if(nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]){
                return nums[mid];
            }

            if(nums[mid] == nums[mid - 1]){
                if((mid - lo + 1) % 2 == 0){
                    lo = mid + 1;
                }else{ 
                    hi = mid - 2;
                }
            }else if(nums[mid] == nums[mid + 1]){
                if((hi - mid + 1) % 2 == 0){
                    hi = mid - 1;
                }else{
                    lo = mid + 2;
                }
            }
        }

        return -1;
    }
}


// Approach-2 (Similar to Approach-1)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;

        if (n == 1)
            return nums[0];

        if (nums[0] != nums[1])
            return nums[0];

        if (nums[n - 1] != nums[n - 2])
            return nums[n - 1];

        int low = 1, high = n - 2;
        while (low <= high) {

            int mid = (low + high) / 2;

            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            if (nums[mid] == nums[mid - 1]) {
                if (mid % 2 == 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[mid] == nums[mid + 1]) {
                if (mid % 2 != 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }

        return -1;
    }
}
