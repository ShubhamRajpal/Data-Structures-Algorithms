/*
  Problem Link: https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i
*/

//Approach  (Sliding Window)
//T.C : O(n)
//S.C : O(n)
class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {

        int n = startTime.length;
        int[] gaps = new int[n + 1];

        // Gaps between consecutive events
        for (int i = 0; i <= n; i++) {
            if (i == 0) gaps[0] = startTime[i];
            else if (i == n) gaps[n] = eventTime - endTime[n - 1];
            else gaps[i] = startTime[i] - endTime[i - 1];
        }

        int maxFreeTime = 0;
        int sum = 0;
        int i = 0, j = 0;

        // Sliding window to find maximum sum of (k+1) gaps
        while (j <= n) {
            sum += gaps[j];

            // Window size should be at most (k+1)
            if (j - i + 1 > k + 1) {
                sum -= gaps[i];
                i++;
            }

            if (j - i + 1 == (k + 1))
                maxFreeTime = Math.max(maxFreeTime, sum);
            j++;
        }

        return maxFreeTime;
    }
}
