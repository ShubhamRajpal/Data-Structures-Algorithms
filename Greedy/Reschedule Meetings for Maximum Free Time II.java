/*
  Problem Link: https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-ii
*/

//Approach (Greedily trying moving each event)
//T.C : O(n)
//S.C : O(n)
class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length;

        // Step 1: Compute freeArray (gaps between events)
        int[] gaps = new int[n + 1];
        gaps[0] = startTime[0];
        gaps[n] = eventTime - endTime[n - 1];
        for (int i = 1; i < n; i++) {
            gaps[i] = startTime[i] - endTime[i - 1];
        }

        // Step 2: Precompute max free to the right
        int[] suffixMaxGap = new int[n + 1];
        suffixMaxGap[n] = gaps[n];
        for (int i = n - 1; i >= 0; i--) {
            suffixMaxGap[i] = Math.max(suffixMaxGap[i + 1], gaps[i]);
        }

        int maxFreeTime = 0;
        
        // Step 3: Precompute max free to the Left using a variable
        int prefixMaxGap = 0;

        // Step 4: Iterate for each possible movement/shift
        for (int i = 0; i < n; i++) {
            int eventDuration = endTime[i] - startTime[i];
            
            // Case 1: Shift left or right
            int maxFreeTimeFromShifts = gaps[i] + gaps[i + 1];
            
            int maxLeftGap = 0, maxRightGap = 0;
            if (i == 0) {
                maxRightGap = suffixMaxGap[i + 2];
            } else if (i == n - 1) {
                maxLeftGap = prefixMaxGap;
            } else {
                maxLeftGap = prefixMaxGap;
                maxRightGap = suffixMaxGap[i + 2];
            }

            int maxFreeTimeFromLeftRightGaps = Math.max(maxLeftGap, maxRightGap);

             // Case 2: Move completely out
            if (maxFreeTimeFromLeftRightGaps >= eventDuration) {
                maxFreeTimeFromShifts = Math.max(maxFreeTimeFromShifts, maxFreeTimeFromShifts + eventDuration);
            }

            maxFreeTime = Math.max(maxFreeTime, maxFreeTimeFromShifts);
            prefixMaxGap = Math.max(prefixMaxGap, gaps[i]);
        }

        return maxFreeTime;
    }
}
