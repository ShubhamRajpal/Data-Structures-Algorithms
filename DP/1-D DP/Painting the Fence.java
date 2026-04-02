/*
  Problem Link: https://www.geeksforgeeks.org/problems/painting-the-fence3727/1
*/

// Approach-1 (recursion + Memoization)
// T.C: O(2^n)
// S.C: O(n) + stack space
class Solution {
    int solve(int n, int k, int[] dp){
        if(n == 1){
            return k;
        }
        
        if(n == 2){
            return k * k;
        }
        
        if(dp[n] != -1)  return dp[n];
        
        
        int last2Same = (k - 1) * solve(n - 2, k, dp);
        int last2Diff = (k - 1) * solve(n - 1, k, dp);
        
        return dp[n] = last2Same + last2Diff;
    }
    int countWays(int n, int k) {
        // code here.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(n, k, dp);
        
    }
}

// Approach-2 (bottom-up)
// T.C: O(n)
// S.C: O(n)
class Solution {
    int countWays(int n, int k) {
        // code here.
        int[] dp = new int[n + 1];
        
        if(n == 1){
            return k;
        }
        
        if(n == 2){
            return k * k;
        }
        
        dp[1] = k;
        dp[2] = k * k;
        
        for(int i = 3; i <= n; i++){
             int last2Same = (k - 1) * dp[i - 2];
             int last2Diff = (k - 1) * dp[i - 1];
        
             dp[i] = last2Same + last2Diff;
        }
        
        return dp[n];
        
    }
}
