/*
  Problem Link:  https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k/
*/

//Approach (Using stream to find sum)
//T.C: O(n)
//S.C: O(1)
class Solution {
    public int minOperations(int[] nums, int k) {
        return Arrays.stream(nums).sum() % k;
    }
}
