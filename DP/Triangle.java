/*
   Problem Link: https://leetcode.com/problems/triangle/    
   Similar Problem:  Solve "Minimum Falling Path Sum" (Leetcode-931) before this question
*/


//Approach-1 Recursion with Memoization - TLE
//T.C : O(n^2) states in dp[][]
//S.C : O(n^2) + recursive Stack Space
class Solution {

    public int findMinMemoize(int i, int j, int n, int[][] dp, List<List<Integer>> triangle){
        if(i == n-1) return dp[n-1][j] = triangle.get(n-1).get(j);

        if(dp[i][j] != -1) return dp[i][j];

        int d = triangle.get(i).get(j) + findMinMemoize(i+1, j, n, dp, triangle);
        int dg = triangle.get(i).get(j) + findMinMemoize(i+1, j+1, n, dp, triangle);
        return dp[i][j] = Math.min(d, dg);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[][] dp = new int[n][n];
        for(int[] it : dp) Arrays.fill(it, -1);
        return findMinMemoize(0, 0, n, dp, triangle);
    }
}



//Approach-2 : Tabulation (Bottom Up - Just like "Minimum Falling Path Sum")
//T.C : O(n^2)
//S.C : O(n^2)
class Solution {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[][] dp = new int[n][n];
        // Calculate for base case
        for(int j = 0; j < n; j++){
            dp[n-1][j] = triangle.get(n-1).get(j);
        }

        for(int i = n-2; i >=0; i--){
            for(int j = i; j >= 0; j--){
                int down = triangle.get(i).get(j) + dp[i+1][j];
                int diagonal = triangle.get(i).get(j) + dp[i+1][j+1];
                dp[i][j] = Math.min(down, diagonal);
            }
        }

        return dp[0][0];
    }
}




//Approach-3 : Space Optmization - Bottom Up
//T.C : O(n^2)
//S.C : O(n)
class Solution {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[] front = new int[n];
        for(int j = 0; j < n; j++){
            front[j] = triangle.get(n-1).get(j);
        }

        for(int i = n-2; i >=0; i--){
            int cur[] = new int[n];
            for(int j = i; j >= 0; j--){
                int d = triangle.get(i).get(j) + front[j];
                int dg = triangle.get(i).get(j) + front[j+1];
                cur[j] = Math.min(d, dg);
            }
            front = cur;
        }

        return front[0];
    }
}
