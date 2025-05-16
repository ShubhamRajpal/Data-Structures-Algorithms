/*
  Problem Link: https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-ii/
*/

//Approach -  Using LIS Pattern
//T.C : (n^2 * L), where L = max length of a string in the words
//S.C : O(n)
class Solution {
    private boolean checkHamDistance(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            cnt += (a.charAt(i) != b.charAt(i)) ? 1 : 0;

            if (cnt > 1) return false;
        }
        return cnt == 1;
    }

    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] parent = new int[n];

        int maxi = 1, lastIndex = 0;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            for (int prev = 0; prev <= i - 1; prev++) {
                if (groups[prev] != groups[i] && words[prev].length() == words[i].length()) {
                    if (checkHamDistance(words[i], words[prev])) {
                        if (1 + dp[prev] > dp[i]) {
                            dp[i] = 1 + dp[prev];
                            parent[i] = prev;
                        }
                    }
                }
            }

            if (dp[i] > maxi) {
                maxi = dp[i];
                lastIndex = i;
            }
        }

        ans.add(words[lastIndex]);
        while (parent[lastIndex] != lastIndex) {
            lastIndex = parent[lastIndex];
            ans.add(words[lastIndex]);
        }

        Collections.reverse(ans);
        return ans;
    }
}
