/*
  Problem Link: https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
*/

// Approach (binary search on answers)
// T.C: O(n log (sum - max)) sum = sum of array, max = maximum value in array
// S.C: O(1)

class Solution {

    public boolean canShip(int midWt, int[] weights, int days) {
        int n = weights.length;

        int curWt = 0, daysCnt = 1;
        for (int i = 0; i < n; i++) {
            if (curWt + weights[i] <= midWt) {
                curWt += weights[i];
            } else {
                daysCnt++;
                curWt = weights[i];

            }
        }

        return daysCnt <= days;
    }

    public int shipWithinDays(int[] weights, int days) {
        int n = weights.length;

        int min = Arrays.stream(weights).max().getAsInt();
        int max = Arrays.stream(weights).sum();

        int low = min, high = max;
        while (low <= high) {

            int mid = (low + high) / 2;
            if (canShip(mid, weights, days)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }

        return low;
    }
}
