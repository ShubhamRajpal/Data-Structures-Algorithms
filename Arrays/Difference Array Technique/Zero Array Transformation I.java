/*
  Problem Link: https://leetcode.com/problems/zero-array-transformation-i
*/

//Approach - Straight Forward Difference Array Technique
//T.C : O(Q + n)
//S.C : O(Q + n)
class Solution {

    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;

        if (Arrays.stream(nums).allMatch(num -> num == 0)) {
            return true;
        }

        int[] diff = new int[n];

        for (int j = 0; j < q; j++) {
            int s = queries[j][0];
            int e = queries[j][1];

            diff[s] += 1;
            if (e + 1 < n) {
                diff[e + 1] -= 1;
            }
        }

        int cumSum = 0;
        for (int k = 0; k < n; k++) {
            cumSum += diff[k];
            diff[k] = cumSum;

            if (nums[k] - diff[k] > 0) {
                return false;
            }
        }

        return true;

    }
}
