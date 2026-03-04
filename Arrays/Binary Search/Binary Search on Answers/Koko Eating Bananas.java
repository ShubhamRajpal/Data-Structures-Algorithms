/*
  Problem Link: https://leetcode.com/problems/koko-eating-bananas/description/
*/

// Approach (binary search on bananas per hours)
// T.C: O(n log n)
// S.C: O(1)

class Solution {

    public static int reqTime(int[] arr, int rate) {

        int totalHrs = 0, n = arr.length;

        for (int i = 0; i < n; i++) {
            totalHrs += Math.ceil((double) arr[i] / (double) rate);
        }

        return totalHrs;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length, maxRate = -1;
        for (int i = 0; i < n; i++) {
            maxRate = Math.max(maxRate, piles[i]);
        }
        int low = 1, high = maxRate;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (reqTime(piles, mid) <= h) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
