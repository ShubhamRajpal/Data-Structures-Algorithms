/*
  Problem Link: https://leetcode.com/problems/find-the-original-typed-string-ii/description/
*/

// Approach-1 (Recursion + Memoization) - TLE
// T.C : O(n*k*maxFreq), n = total unique characters, maxFreq = maximumFrequency of a character
// S.C : O(n*k)
class Solution {

    int MOD = (int) (1e9 + 7);

    public long solve(int ind, int count, List<Integer> freq, int k, int[][] dp) {
        if (ind >= freq.size()) {
            if (count < k) {
                return 1;
            }
            return 0;
        }

        if (dp[ind][count] != -1)
            return dp[ind][count];

        long res = 0;

        for (int i = 1; i <= freq.get(ind); i++) {
            if (count + i < k) {
                res = (res + solve(ind + 1, count + i, freq, k, dp)) % MOD;
            } else {
                break;
            }
        }

        return dp[ind][count] = (int) res;
    }

    public int possibleStringCount(String word, int k) {

        int n = word.length();
        List<Integer> freq = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                freq.add(count);
                count = 1;
            }
        }

        freq.add(count);

        int m = freq.size();

        long p = 1;
        for (int f : freq) {
            p = (p * f) % MOD;
        }

        if (m >= k) {
            return (int) (p % MOD);
        }

        long invalidStrings = 0;
        int[][] dp = new int[m+1][k];
        for(int it[] : dp) Arrays.fill(it, -1);

        invalidStrings = solve(0, 0, freq, k, dp);


        return (int) ((p - invalidStrings + MOD) % MOD);

    }
}

// Approach-2 (Bottom Up) - TLE
// T.C : O(n*k*maxFreq), n = total unique characters, maxFreq = maximumFrequency of a character
// S.C : O(n*k
class Solution {

    int MOD = (int) (1e9 + 7);

    public int possibleStringCount(String word, int k) {

        int n = word.length();

        if(k > n)  return 0;
        List<Integer> freq = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                freq.add(count);
                count = 1;
            }
        }
        freq.add(count);

        long p = 1;
        for (int f : freq) {
            p = (p * f) % MOD;
        }

        int m = freq.size();
        if (m >= k) {
            return (int) p;
        }

        int[][] dp = new int[m + 1][k];

        for (int count1 = k - 1; count1 >= 0; count1--) {
            dp[m][count1] = 1;
        }

        for (int ind = m - 1; ind >= 0; ind--) {
            for (int count2 = k - 1; count2 >= 0; count2--) {
                long res = 0;

                for (int take = 1; take <= freq.get(ind); take++) {
                    if (count2 + take < k) {
                        res = (res + dp[ind + 1][count2 + take]) % MOD;
                    } else {
                        break;
                    }
                }

                dp[ind][count2] = (int) (res);
            }
        }
        long invalidStrings = dp[0][0];

        return (int) ((p - invalidStrings + MOD) % MOD);

    }
}

// Approach-3 (Bottom Up) - Optimized using Prefix Sum
// T.C : O(n*k)
// S.C : O(n*k)
class Solution {

    int MOD = (int) (1e9 + 7);

    public int possibleStringCount(String word, int k) {

        int n = word.length();

        if (k > n) return 0;

        List<Integer> freq = new ArrayList<>();
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                freq.add(count);
                count = 1;
            }
        }
        freq.add(count);

        long p = 1;
        for (int f : freq) {
            p = (p * f) % MOD;
        }

        int m = freq.size();
        if (m >= k) {
            return (int) p;
        }

        int[][] dp = new int[m + 1][k];

        for (int count1 = k - 1; count1 >= 0; count1--) {
            dp[m][count1] = 1;
        }

        for (int ind = m - 1; ind >= 0; ind--) {

            int[] prefix = new int[k + 1];
            for (int j = 1; j <= k; j++) {
                prefix[j] = (prefix[j - 1] + dp[ind + 1][j - 1]) % MOD;
            }

            for (int count2 = k - 1; count2 >= 0; count2--) {

                int l = count2 + 1;
                int r = count2 + freq.get(ind);

                if (r + 1 > k) {
                    r = k - 1;
                }

                if (l <= r) {
                    dp[ind][count2] = (prefix[r + 1] - prefix[l] + MOD) % MOD;
                }

            }
        }

        long invalidStrings = dp[0][0];

        return (int) ((p - invalidStrings + MOD) % MOD);

    }
}
