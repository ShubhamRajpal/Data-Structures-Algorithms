/*
  Problem Link: https://leetcode.com/problems/find-all-possible-stable-binary-arrays-i/
*/

//Approach-1 (Recursion + Memoization)
//T.C : O(one * zero * limit)
//S.C : O(one * zero)
class Solution {

    int mod = (int) (1e9 + 7);

    public int solve(int zeros, int ones, int lastWasOne, int limit, int[][][] dp) {

        if (zeros == 0 && ones == 0) {
            return 1;
        }


        if (dp[zeros][ones][lastWasOne] != -1) {
            return dp[zeros][ones][lastWasOne];
        }

        int ans = 0;
        if (lastWasOne == 1) {
            // Explore 0s
            for (int i = 1; i <= Math.min(zeros, limit); i++) {
                ans = (ans + solve(zeros - i, ones, 0, limit, dp)) % mod;
            }
        } else {
            // Explore 1s
            for (int i = 1; i <= Math.min(ones, limit); i++) {
                ans = (ans + solve(zeros, ones - i, 1, limit, dp)) % mod;
            }
        }

        return dp[zeros][ones][lastWasOne] = ans % mod;
    }

    public int numberOfStableArrays(int zero, int one, int limit) {

        String temp = "";
        int[][][] dp = new int[zero + 1][one + 1][2];
        for (int[][] it1 : dp) {
            for (int[] it2 : it1)
                Arrays.fill(it2, -1);
        }

        int startWithOne = solve(zero, one, 1, limit, dp);
        int startWithZero = solve(zero, one, 0, limit, dp);

        return (startWithOne + startWithZero) % mod;

    }
}

//Approach-2 (Bottom Up)
//T.C : O(one * zero * limit)
//S.C : O(one * zero)
class Solution {

    int mod = (int) (1e9 + 7);

    public int numberOfStableArrays(int zero, int one, int limit) {

        int[][][] dp = new int[zero + 1][one + 1][2];
        for (int[][] it1 : dp) {
            for (int[] it2 : it1)
                Arrays.fill(it2, -1);
        }

        // base case
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;

        for (int zeros = 0; zeros <= zero; zeros++) {
            for (int ones = 0; ones <= one; ones++) {

                if (zeros == 0 && ones == 0) {
                    continue;
                }

                int res = 0;
                // Explore 0s if last we used was '1'
                for (int i = 1; i <= Math.min(zeros, limit); i++) {
                    res = (res + dp[zeros - i][ones][0]) % mod;
                }

                dp[zeros][ones][1] = res % mod;

                res = 0;
                // Explore 1s if last we used was '0'
                for (int i = 1; i <= Math.min(ones, limit); i++) {
                    res = (res + dp[zeros][ones - i][1]) % mod;
                }

                dp[zeros][ones][0] = res % mod;
            }
        }

        return (dp[zero][one][0] + dp[zero][one][1]) % mod;

    }
}
