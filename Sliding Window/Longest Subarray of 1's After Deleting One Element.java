/*
  Problem Link: https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/
*/

//Approach-1 (Using Traditional Sliding Window) - O(2*n)
T.C: O(2*n)
S.C: O(1)
class Solution {
    public int longestSubarray(int[] nums) {
        int zeroCount = 0;
        int maxi = 0;
        int n = nums.length;
        int i = 0, j = 0;

        while (j < n) {
            zeroCount += nums[j] == 0 ? 1 : 0;

            while (zeroCount > 1) {
                if(nums[i] == 0) zeroCount--;
                i++;
            }

            maxi = Math.max(maxi, j - i);
            j++;
        }

        return maxi;
    }
}



//Approach-2 (Using Traditional Sliding Window) - O(n)
T.C: O(n)
S.C: O(1)
class Solution {
    public int longestSubarray(int[] nums) {
        int i = 0, j = 0;
        
        int last_zero_idx = -1;
        
        int result = 0;
        
        while(j < nums.length) {
            
            if(nums[j] == 0) {
                i = last_zero_idx+1;
                last_zero_idx = j;
            }
            
            result = Math.max(result, j-i);
            j++;
        }
        
        return result;
    }
}
