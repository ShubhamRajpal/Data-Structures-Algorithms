/*
  Problem Link: https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
*/

// Approach (binary search on days)
// T.C: O(n log n)
// S.C: O(1)
class Solution {

    public boolean canMakeBouqets(int midDay, int[] bloomDay, int m, int k) {
        int n = bloomDay.length;

        int consecutiveFlowerCnt = 0, bouquets = 0;
        for (int i = 0; i < n; i++) {
            if (bloomDay[i] <= midDay) {
                consecutiveFlowerCnt++;
            } else {
                bouquets += ((long) consecutiveFlowerCnt / k);
                consecutiveFlowerCnt = 0;
            }
        }
        bouquets += ((long) consecutiveFlowerCnt / k);
        return bouquets >= m;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;

        if ((1L * m * k) > n) {
            return -1;
        }

        int low = Arrays.stream(bloomDay).min().getAsInt();
        int high = Arrays.stream(bloomDay).max().getAsInt();

        while (low <= high) {

            int midDays = (low + high) / 2;

            if (canMakeBouqets(midDays, bloomDay, m, k)) {
                high = midDays - 1;
            } else {
                low = midDays + 1;
            }
        }

        return low;
    }
}
