/*
  Problem Link: https://leetcode.com/problems/distribute-candies-among-children-i/
*/

Approach-1 (using Recursion - Try all possible ways of distributing candies)
//T.C : O(limit^3)
//S.C : O(1)
class Solution {
    private int solve(int childCount, int candiesRemaining, int limit) {
        if (childCount == 3) {
            if (candiesRemaining == 0) {
                return 1;
            }
            return 0;
        }

        int count = 0;
        for (int candies = 0; candies <= Math.min(limit, candiesRemaining); candies++) {
            count += solve(childCount + 1, candiesRemaining - candies, limit);
        }

        return count;

    }

    public int distributeCandies(int n, int limit) {
        return solve(0, n, limit);
    }
}


Approach-2 (Iterative - using 2 Loops)
//T.C : O(limit^2)
//S.C : O(1)
class Solution {
    public int distributeCandies(int n, int limit) {
        int cnt = 0;
        for (int i = 0; i <= Math.min(n, limit); i++) {
            for (int j = 0; j <= Math.min(limit, n - i); j++) {
                int k = n - (i + j);
                int sum = i + j + k;
                if (sum == n && k <= limit)
                    cnt++;
            }
        }

        return cnt;
    }
}

//Approach-3 - Using maths distribution
//T.C : O(n)
//S.C : O(1)
class Solution {
    public long distributeCandies(int n, int limit) {
        long ways = 0;

        // Loop through possible candies for the first child
        int minCh1 = Math.max(0, n - 2 * limit);
        int maxCh1 = Math.min(n, limit);

        for (int i = minCh1; i <= maxCh1; i++) {
            int remaining = n - i;

            int minCh2 = Math.max(0, remaining - limit);
            int maxCh2 = Math.min(remaining, limit);

            ways += (maxCh2 - minCh2 + 1);
        }

        return ways;
    }
}
