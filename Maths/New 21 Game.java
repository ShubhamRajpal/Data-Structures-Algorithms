/*
  Problem Link: https://leetcode.com/problems/new-21-game/
*/

//Approach-1 (Brute Force using DP Tabulation) - TLE
T.C: O(n*maxPts)
S.C: O(n)
class Solution {

    public double new21Game(int n, int k, int maxPts) {

        double[] dp = new double[n + 1];
        dp[0] = 1;

        for (int curPts = 1; curPts <= n; curPts++) {
            for (int idx = 1; idx <= maxPts; idx++) {
                if (curPts-idx >= 0 && curPts-idx < k) {
                    dp[curPts] += (dp[curPts - idx] / maxPts);
                }
            }
        }

        double sum = 0.0;
        for (int i = n; i >= k; i--) {
            sum += dp[i];
        }

        return sum;

    }
}




//Approach-2 (Optimising above solution to use already solve subproblem + sliding window to remove unwanted number cards(< curPts-maxPts) for next card calculation)
T.C: O(n)
S.C: O(n)
class Solution {
public double new21Game(int n, int k, int maxPts) {
        
        double[] dp = new double[n + 1];
        //dp[i] = probability of getting score = i
        
        dp[0] = 1; //Since already initally she has score = 0, hence probability for scoring 0 is 1
        
        if(k == 0 || n >= k+maxPts)  return 1.0; // Since every card can be picked up once to reach sum >= k so dp[i] = 1/maxPts (1 <= i < maxPts)
        double sum = k == 0 ? 0 : 1;
        double ans = 0.0;
        for (int curPts = 1; curPts <= n; curPts++) {
            dp[curPts] = sum/maxPts;
            if(curPts < k){
                sum += dp[curPts];
            }else{
                ans += sum/maxPts;
            }

            if(curPts-maxPts >= 0 && curPts-maxPts < k){
                sum -= dp[curPts-maxPts];
            }
            
        }

      return ans;

    }
}
