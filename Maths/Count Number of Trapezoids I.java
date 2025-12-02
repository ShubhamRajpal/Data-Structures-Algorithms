/*
  Problem Link: https://leetcode.com/problems/count-number-of-trapezoids-i/
*/

//Approach - Simple Maths counting
//T.C : O(n)
//S.C : O(n)
class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        int mod = (int) 1e9 + 7;

        Map<Integer, Integer> groups = new HashMap<>();

        for (int i = 0; i < n; i++) {
            groups.put(points[i][1], groups.getOrDefault(points[i][1], 0) + 1);
        }

        long prevHorizontalLines = 0, res = 0;

        for (int numY : groups.values()) {
            if (numY > 1) {
                long curHorizontalLines = ((long) numY * (numY - 1)) / 2;
                res = (res + curHorizontalLines * prevHorizontalLines) % mod;
                prevHorizontalLines = (prevHorizontalLines + curHorizontalLines) % mod;
            }
        }

        return (int) res;

    }
}
