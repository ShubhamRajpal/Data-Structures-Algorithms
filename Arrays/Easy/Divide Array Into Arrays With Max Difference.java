/*
  Problem Link:  https://leetcode.com/problems/divide-array-into-arrays-with-max-difference/
*/

Approach (using Sorting + Greedily comparing first and last element of each array)
//T.C : O(nlogn)
//S.C : O(1)
class Solution {
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int[][] ans = new int[n / 3][3];
        int K = 0;
        for (int i = 0; i < n; i += 3) {
            if (nums[i + 2] - nums[i] > k) {
                return new int[0][0];
            }

            ans[K++] = new int[] { nums[i], nums[i + 1], nums[i + 2] };
        }

        return ans;

    }
}
