/*
  Problem Link: https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/
*/

// Approach (binary search on answers)
// T.C: O(n log max) max = maximum value in array
// S.C: O(1)
class Solution {

    public boolean check(int midDivisor, int[] nums, int t) {

        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.ceil((double) nums[i] / (double) midDivisor);
        }

        return sum <= t;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int n = nums.length;

        int maxDivisor = Arrays.stream(nums).max().getAsInt();

        int low = 1, high = maxDivisor;
        while (low <= high) {

            int midDivisor = (low + high) / 2;

            if (check(midDivisor, nums, threshold)) {
                high = midDivisor - 1;
            } else {
                low = midDivisor + 1;
            }
        }

        return low;
    }
}
