
/*
  Problem Link: https://leetcode.com/problems/minimum-time-to-repair-cars/?envType=daily-question&envId=2025-03-16
  T.C. - O(n* log maxMinutes)
  S.C. - O(1)
*/
class Solution {

    private boolean canBeRepaired(long mid, int[] ranks, int k) {
        long carCount = 0;
        for (int i = 0; i < ranks.length; i++) {
            carCount += Math.sqrt(mid / ranks[i]);
        }

        return carCount >= k;
    }

    public long repairCars(int[] ranks, int cars) {
        int n = ranks.length;
        long maxMinutes = Arrays.stream(ranks).max().getAsInt();
        long l = 1, r = maxMinutes * cars * cars;
        long ans = 0;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (canBeRepaired(mid, ranks, cars)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }
}
