/*
  Problem Link: https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/
*/

//Approach-2 (Using Sorting + Two Pointer Approach)
//T.C : O(nlogn)
//S.C : O(n)
class Solution {
    public int numSubseq(int[] nums, int target) {

        int n = nums.length;
        int ans = 0;
        int mod = (int) 1e9 + 7;

        // Sort the array
        Arrays.sort(nums);

        //Pre-compute the powers of 2 to avoid going out of range
        int[] powerOf2 = new int[n];
        powerOf2[0] = 1;
        for (int i = 1; i < n; i++) {
            powerOf2[i] = (powerOf2[i - 1] * 2) % mod;
        }

        int l = 0, r = n - 1;
        while (l <= r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else {
                ans = (ans + powerOf2[r - l]) % mod;
                l++;
            }
        }

        return ans;
    }
}
