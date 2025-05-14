/*
    Problem Link: https://leetcode.com/problems/count-number-of-balanced-permutations
*/

// Approach-1 (Brute Force)
// T.C : O(n * n!)
// S.C : O(n * n!), storing all permutations in set
class Solution {
    public int countBalancedPermutations(String num) {
        char[] chars = num.toCharArray();
        Arrays.sort(chars); // Sorting helps next_permutation to find all permutations, so we need to start from smallest (hence sorted)
        Set<String> seen = new HashSet<>();
        int count = 0;

        do {
            String s = new String(chars);
            if (seen.contains(s))
                continue;
            seen.add(s);
            int evenSum = 0, oddSum = 0;
            for (int i = 0; i < chars.length; ++i) {
                int digit = chars[i] - '0';
                if (i % 2 == 0) evenSum += digit;
                else oddSum += digit;
            }
            if (evenSum == oddSum) {
                count++;
            }
        } while (nextPermutation(chars));

        return count;
    }

    // Helper method for next permutation
    private boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) i--;
        if (i < 0) return false;
        int j = arr.length - 1;
        while (arr[j] <= arr[i]) j--;
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        reverse(arr, i + 1, arr.length - 1);
        return true;
    }

    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char tmp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = tmp;
        }
    }
}


// Approach-2 (Recursion + Memoization)
// T.C : O(10 * n^2 * Sum)
// S.C : O(10 ^ n * Sum)
class Solution {
    int mod = (int) 1e9 + 7;
    long totalPermPossible = 0;
    int totalDigitSum;
    int n;

    // Binary exponentiation
    public int findPow(long a, long b) {
        if (b == 0) {
            return 1;
        }

        long half = findPow(a, b / 2);
        long result = (half * half) % mod;
        if (b % 2 == 1) {
            result = (result * a) % mod;
        }

        return (int) result;
    }

    public int solve(int digit, int evenIndexDigitCount, int curSum, int[] freq, long[] fermatFact, int[][][] dp) {
        if (digit == 10) {
            if (curSum == totalDigitSum/2 && evenIndexDigitCount == (n + 1) / 2)
                return (int) totalPermPossible;
            return 0;
        }

        if(dp[digit][evenIndexDigitCount][curSum] != -1)  return dp[digit][evenIndexDigitCount][curSum];

        long ways = 0;

        for (int count = 0; count <= Math.min(freq[digit], (n + 1) / 2 - evenIndexDigitCount); count++) {
            int evenPosCount = count;
            int oddPosCount = freq[digit] - count;

            long div = (fermatFact[evenPosCount] * fermatFact[oddPosCount]) % mod;

            long value = solve(digit + 1, evenIndexDigitCount + evenPosCount, curSum + digit * count, freq, fermatFact, dp);

            ways = (ways + (value * div) % mod) % mod;

        }

        return dp[digit][evenIndexDigitCount][curSum] = (int) ways;
    }

    public int countBalancedPermutations(String num) {
        n = num.length();
        totalDigitSum = 0;
        int[] freq = new int[10];
        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';
            totalDigitSum += digit;
            freq[digit]++;
        }

        if (totalDigitSum % 2 != 0)
            return 0;

        long[] fact = new long[n + 1];

        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % mod;
        }

        long[] fermatFact = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            fermatFact[i] = findPow(fact[i], mod - 2) % mod;
        }

        totalPermPossible = (fact[(n + 1) / 2] * fact[n / 2]) % mod;

        int[][][] dp = new int[10][(n + 1) / 2 + 1][totalDigitSum + 1];
        for (int[][] arr2D : dp)
            for (int[] arr1D : arr2D)
                Arrays.fill(arr1D, -1);

        int digit = 0;
        int curSum = 0;
        int evenIndexDigitCount = 0;
        return solve(digit, evenIndexDigitCount, curSum, freq, fermatFact, dp);

    }
}
