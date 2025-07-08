/*
  Problem Link: https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/description
*/

//Approach-1 (Using Recursion + Memo) using Linear Search for next event - T.C. - O(n*k*n)
/*
  We visit at most n*k states (size of memoization array) - O(n*k)
  At every state, we do a linear search - which takes O(n) time
*/
class Solution {

    public int solve(int ind, int k, int[][] events, int[][] dp) {
        if (ind >= events.length || k == 0) {
            return 0;
        }

        if (dp[ind][k] != -1)
            return dp[ind][k];

        int notTake = solve(ind + 1, k, events, dp);
        int take = Integer.MIN_VALUE;
        int j = ind + 1;
        for (; j < events.length; j++) {
            if (events[j][0] > events[ind][1]) {
                break;
            }
        }
        take = events[ind][2] + solve(j, k - 1, events, dp);

        return dp[ind][k] = Math.max(take, notTake);
    }

    public int maxValue(int[][] events, int k) {

        int n = events.length;
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int[][] dp = new int[n + 1][k + 1];
        for (int[] it : dp) {
            Arrays.fill(it, -1);
        }
        return solve(0, k, events, dp);
    }
}


//Approach-2 (Using Recursion + Memo) using Binary Search for next event 
T.C. - O(n*k*log(n))
class Solution {

    private int binarySearch(int idx, int targetStart, int[][] events) {
        int low = idx, high = events.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (events[mid][0] > targetStart) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;

    }

    public int solve(int ind, int k, int[][] events, int[][] dp) {
        if (ind == -1 || ind >= events.length || k == 0) {
            return 0;
        }

        if (dp[ind][k] != -1) {
            return dp[ind][k];
        }

        int notTake = solve(ind + 1, k, events, dp);
        int take = Integer.MIN_VALUE;
        int nextStart = binarySearch(ind + 1, events[ind][1], events);

        take = events[ind][2] + solve(nextStart, k - 1, events, dp);

        return dp[ind][k] = Math.max(take, notTake);
    }

    public int maxValue(int[][] events, int k) {

        int n = events.length;
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int[][] dp = new int[n + 1][k + 1];
        for (int[] it : dp) {
            Arrays.fill(it, -1);
        }
        return solve(0, k, events, dp);
    }
}
