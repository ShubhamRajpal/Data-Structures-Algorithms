/*
  Problem Link: https://leetcode.com/problems/count-the-number-of-computer-unlocking-permutations/
*/

//Approach - Simple observation and maths factorial
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int countPermutations(int[] complexity) {
        int n = complexity.length;

        long ans = 1;
        int mod = (int) 1e9 + 7;
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= complexity[0]) {
                return 0;
            }
            ans = (ans * i) % mod;
        }

        return (int) ans;

    }
}
