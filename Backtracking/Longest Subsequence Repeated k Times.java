/*
  Problem Link: https://leetcode.com/problems/longest-subsequence-repeated-k-times/
*/

//Approach-1 (Khandani Backtracking remplate - storing all possible strings)
//T.C : O(n * ((n/k)!))
//S.C : O(n/k)
class Solution {
    String result = "";

    // Check if seq * k is a subsequence of s
    private boolean isSubsequence(String s, String sub, int k) {
        int i = 0, j = 0, len = sub.length(), n = s.length();

        while (i < n && j < k * len) {
            if (s.charAt(i) == sub.charAt(j % len)) {
                j++;
            }
            i++;
        }

        return j == k * len;
    }

    private void backtracking(String s, StringBuilder curr, boolean[] canUse, int[] requiredFreq, int k, int maxLen) {
        if (curr.length() > maxLen) return;

        String currStr = curr.toString();
        if ((curr.length() > result.length() || 
             (curr.length() == result.length() && currStr.compareTo(result) > 0)) &&
            isSubsequence(s, currStr, k)) {
            result = currStr;
        }

        for (int i = 25; i >= 0; i--) { // from 'z' to 'a' for lexicographically larger result
            if (!canUse[i] || requiredFreq[i] == 0) continue;

            curr.append((char) (i + 'a'));
            requiredFreq[i]--;

            backtracking(s, curr, canUse, requiredFreq, k, maxLen);

            curr.deleteCharAt(curr.length() - 1);
            requiredFreq[i]++;
        }
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        boolean[] canUse = new boolean[26];
        int[] requiredFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            if (freq[i] >= k) {
                canUse[i] = true;
                requiredFreq[i] = freq[i] / k;
            }
        }

        int maxLen = n / k;
        StringBuilder curr = new StringBuilder();
        backtracking(s, curr, canUse, requiredFreq, k, maxLen);

        return result;
    }
}

//Approach-2 IMPROVED BACKTRACKING : (Khandani Backtracking remplate - Greedily trying strings from maxLen- n/k)
//T.C : O(n * ((n/k)!))
//S.C : O(n/k)
class Solution {

    private boolean isSubsequence(String s, String cur, int k) {
        int L = cur.length();
        int n = s.length();
        int j = 0;
        int maxLen = k * L;
        for (int i = 0; i < n && j < maxLen; i++) {
            if (s.charAt(i) == cur.charAt(j % L)) {
                j++;
            }
        }

        return j == maxLen;
    }

    private boolean solve(String s, StringBuilder cur, int[] requiredFreq, String[] result,
            int maxLen,
            int k) {

        if (cur.length() == maxLen) {
            if (isSubsequence(s, cur.toString(), k)) {
                result[0] = cur.toString();
                return true;
            }
            return false;
        }

        for (int i = 25; i >= 0; i--) {
            if (requiredFreq[i] == 0)
                continue;

            char ch = (char) (i + 'a');
            cur.append(ch);
            requiredFreq[ch - 'a']--;

            if (solve(s, cur, requiredFreq, result, maxLen, k))
                return true;

            cur.deleteCharAt(cur.length() - 1);
            requiredFreq[ch - 'a']++;
        }

        return false;
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] requiredFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            if (freq[i] >= k) {
                requiredFreq[i] = freq[i] / k; // atmost these many chars can be used in the subsequence
            }
        }

        int maxLen = n / k;
        String[] result = new String[1];
        result[0] = "";
        for (int i = maxLen; i >= 1; i--) {
            StringBuilder cur = new StringBuilder();
            if (solve(s, cur, requiredFreq, result, i, k)) {
                return result[0];
            }
        }

        return result[0];

    }
}
