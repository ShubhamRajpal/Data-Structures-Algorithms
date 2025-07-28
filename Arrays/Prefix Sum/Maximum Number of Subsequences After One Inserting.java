/*
  Problem Link: https://leetcode.com/problems/maximum-number-of-subsequences-after-one-inserting/description/
*/

// Approach (Precompute preL, preLC, sufT, and sufCT arrays to count L’s, LC’s, T’s, and CT’s at each position. )
// T.C: O(n)
// S.C: O(n)
class Solution {
    public long numOfSubsequences(String s) {
        int n = s.length();

        long[] prefixL = new long[n];
        prefixL[0] = s.charAt(0) == 'L' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            prefixL[i] = prefixL[i - 1] + (s.charAt(i) == 'L' ? 1 : 0);
        }

        long[] suffixT = new long[n];
        suffixT[n - 1] = s.charAt(n - 1) == 'T' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            suffixT[i] = suffixT[i + 1] + (s.charAt(i) == 'T' ? 1 : 0);
        }

         // Precompute original no of "LCT" subsequences 
        long originalCnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'C') {
                originalCnt += (prefixL[i] * suffixT[i]);
            }
           
        }

        // Find total increase in count of subsequences after inserting 'L' at start
        long gainL = 0, countL = 1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'L') {
                countL++;
            } else if (s.charAt(i) == 'C') {
                gainL += (suffixT[i] * countL);
            }
        }

        long totalIncreaseAfterAddingL = gainL - originalCnt;

        // Find total increase in count of subsequences after inserting 'T' at end
        long gainT = 0, countT = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'T') {
                countT++;
            } else if (s.charAt(i) == 'C') {
                gainT += (prefixL[i] * countT);
            }
        }

        long totalIncreaseAfterAddingT = gainT - originalCnt;

        // Find total increase in count of subsequences after inserting 'C'
        long gainC = -1, ans = -1;
        for (int i = 0; i < n; i++) {
            if (gainC < (suffixT[i] * prefixL[i])) {
                gainC = suffixT[i] * prefixL[i];
            }
            ans = Math.max(gainC, Math.max(totalIncreaseAfterAddingL, totalIncreaseAfterAddingT));
        }

        // Return ans as count of original "LCT" subsequences + max count from all 3 cases 
        return ans + originalCnt;

    }
}
