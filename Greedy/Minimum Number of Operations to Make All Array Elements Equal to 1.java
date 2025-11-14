/*
  Problem Link: https://leetcode.com/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/
*/

//Approach - Turn an element to 1 and make all elements 1 using it.
//T.C : O(n^2 * log(M)), M = maximum number
//S.C : O(1)
class Solution {

    private int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }

    public int minOperations(int[] nums) {
        int n = nums.length;
        int cntOnes = 0;
        int minGCD = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                cntOnes++;
            }

            minGCD = GCD(nums[i], minGCD);
        }

        if (cntOnes > 0)
            return n - cntOnes;

        if(minGCD > 1) return -1;    

        int minLen = n;
        for (int i = 0; i < n - 1; i++) {
            int gcd = nums[i];

            for (int j = i; j < n; j++) {
                int curGCD = GCD(nums[j], gcd);
                if (curGCD == 1) {
                    minLen = Math.min(minLen, j - i);
                }
                gcd = curGCD;
            }
        }

        return minLen + n - 1;

    }
}
