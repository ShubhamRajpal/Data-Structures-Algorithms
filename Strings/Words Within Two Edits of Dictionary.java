/*
  Problem Link: https://leetcode.com/problems/words-within-two-edits-of-dictionary/
*/

//Approach (Iterate and check)
//T.C : O(q * d * n), q = queries.size(), d = dictionary.size(), n = query[i].length()
//S.C : O(1)
class Solution {

    public boolean canEqualize(String src, String target) {
        int n = src.length();
        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (src.charAt(i) != target.charAt(i)) {
                diff++;
            }

            if(diff > 2) break;
        }

        return diff <= 2;
    }

    public boolean check(String cur, String[] dictionary) {

        for (String s : dictionary) {
            if (canEqualize(cur, s)) {
                return true;
            }
        }

        return false;
    }

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        int n = queries.length;
        List<String> ans = new ArrayList<>();

        for (String q : queries) {

            if (check(q, dictionary)) {
                ans.add(q);
            }
        }

        return ans;

    }
}
