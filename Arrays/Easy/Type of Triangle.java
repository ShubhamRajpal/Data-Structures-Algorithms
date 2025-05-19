/*
  Problem Link: https://leetcode.com/problems/type-of-triangle
*/

//Approach (Simply checking triangle properties)
//T.C : O(1) 
//S.C : O(1)
class Solution {
    public String triangleType(int[] nums) {

        if (nums[0] + nums[1] <= nums[2] || nums[1] + nums[2] <= nums[0] || nums[0] + nums[2] <= nums[1]) {
            return "none";
        }

        if (nums[0] == nums[1] && nums[1] == nums[2]) {
            return "equilateral";
        } else if (nums[0] != nums[1] && nums[1] != nums[2] && nums[0] != nums[2]) {
            return "scalene";
        }
        return "isosceles";

    }
}
