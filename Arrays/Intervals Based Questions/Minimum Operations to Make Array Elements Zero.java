/*
  Problem Link: https://leetcode.com/problems/minimum-operations-to-make-array-elements-zero/
*/
//Approach (Using Power Four Range for Steps of Reduction to 0)
//T.C : O(Q * log(max r)), Q =  number of queries, r = right value of ranges
//S.C : O(1)
class Solution {

    private long solve(int l, int r) {
        long L = 1;  // starting point of range
        long S = 1;  // steps multiplier
        long steps = 0;

        while (L <= r) {
            long R = 4 * L - 1;  // end of current range

            long start = Math.max(L, (long) l);
            long end = Math.min(R, (long) r);

            if (start <= end) {
                steps += (end - start + 1) * S;
            }

            S += 1;
            L = L * 4;
        }

        return steps;
    }

    public long minOperations(int[][] queries) {
        long result = 0;

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            long steps = solve(l, r);
            result += (steps + 1) / 2;
        }

        return result;
    }
}
