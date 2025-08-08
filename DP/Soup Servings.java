/*
  Problem Link: https://leetcode.com/problems/soup-servings/
*/

//Approach-1 (Recursion + Memoization) + Trick
class Solution {
    public double solve(int a, int b, int[][] operations, double[][] dp){
        if(a <= 0 && b <= 0){
            return 0.5;
        }

        if(b <= 0){
            return 0.0;
        }

        if(a <= 0){
            return 1.0;
        }

        if(dp[a][b] != -1)  return dp[a][b];

        double probability = 0.0;
        for(int[] op : operations){
           probability += 0.25 * solve(a - op[0], b - op[1], operations, dp);
        }


        return dp[a][b] = probability;

    }

    public double soupServings(int n) {
        int a = n, b = n;

        if(n >= 5000)  return 1.0;

        int[][] operations = { { 25, 75 }, { 50, 50 }, { 75, 25 }, { 100, 0 } };
        double[][] dp = new double[n+1][n+1];
        for(double[] it : dp) Arrays.fill(it,-1.0);
        return solve(a, b, operations, dp);
    }
}
