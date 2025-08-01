/*
  Problem Link: https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k
*/

//Approach (Using sorting + greedily partitioning)
//T.C : O(nlogn + n)
//S.C : O(1)
class Solution {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;

        int minVal = nums[0];
        int count = 1;

        for (int i = 0; i < n; i++) {
            if (nums[i] - minVal > k) {
                count++;
                minVal = nums[i];
            }
        }

        return count;
    }
}
