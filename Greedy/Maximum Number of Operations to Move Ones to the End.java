/*
  Problem Link: https://leetcode.com/problems/maximum-number-of-operations-to-move-ones-to-the-end/
*/

//Approach - Greedily moving from left to right
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int maxOperations(String s) {
        int n = s.length();
        int ans = 0;
        int cntOnes = s.charAt(0) == '1' ? 1 : 0;
        for(int i = 1;i < n; i++){

            if(s.charAt(i) == '0') continue;
            if(s.charAt(i) == '1' && s.charAt(i-1) == '0'){
                ans += cntOnes;
            }

            cntOnes++;
        }

        ans += (s.charAt(n-1) == '0') ? cntOnes : 0;
        return ans;
    }


}
