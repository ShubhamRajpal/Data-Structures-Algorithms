/*  
  T.C- O(NlogN)
  S.C - O(1)
  Problem Link:  https://leetcode.com/problems/count-the-number-of-fair-pairs/description/
*/

class Solution {

    public int findBound(int i, int target, int[] nums, int lower, int upper, int check){
        int lo = i, hi = nums.length - 1;
        while(lo <= hi){
            int mid = lo + ((hi - lo) >> 1);
            if(check == 0){    
                if(nums[mid] >= target){   
                    hi = mid - 1;
                }else{
                    lo = mid + 1; 
                }
            }else{
                if(nums[mid] <= target){
                    lo = mid + 1;
                }else{
                    hi = mid - 1;
                }
            }    
        }

        return check == 0 ? lo-1 : hi;
    }


    public long countFairPairs(int[] nums, int lower, int upper) {
        int n = nums.length;
        Arrays.sort(nums);
        long cnt = 0;
        int i = 0;
        while(i < n){
            int lowerBound = findBound(i+1, lower - nums[i], nums, lower, upper, 0);
            int upperBound = findBound(i+1, upper -  nums[i], nums, lower, upper, 1);
            cnt += (upperBound - lowerBound);
            i++;
        }
        return cnt;
    }
}
