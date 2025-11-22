/*
  Problem Link: https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/
*/

/************************************************************************ Java ************************************************************/
// Approach (Check if num yields some rem or not)
// T.C: O(n)
// S.C: O(1)
class Solution {
    public int minimumOperations(int[] nums) {
        int n  = nums.length;        
        int ops = 0;

        for(int i : nums){
            if(i % 3 != 0){
                ops += 1;
            }
        }

        return ops;
    }
}

/************************************************************************ Javascript ************************************************************/
// Approach (Using reduce method: Check if num yields some rem or not)
// T.C: O(n)
// S.C: O(1)
var minimumOperations = function(nums) {
    return nums.reduce(function(acc, next){
        acc += next % 3 != 0 ? 1 : 0;
        return acc;
    }, 0);
}
