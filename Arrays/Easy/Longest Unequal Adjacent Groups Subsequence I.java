/*
  Problem Link: https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i
*/

//Approach (Counting groups)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        List<String> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i == 0 || groups[i] != groups[i - 1]) {
                result.add(words[i]); // new group started, so pick the first element
            }
        }

        return result;
    }
}
