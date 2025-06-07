/*
  Problem Link: https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
*/

// Approach-1 (DFS)
//T.C- O(M * (n+m))
//S.C- O(n+m)
class Solution {

    private char dfs(int ch, int[] vis, List<List<Integer>> adj) {
        vis[ch] = 1;

        char minChar = (char) (ch + 'a');

        for (int adjChar : adj.get(ch)) {
            if (vis[adjChar] == 0) {
                minChar = (char) Math.min(minChar, dfs(adjChar, vis, adj));
            }
        }

        return minChar;
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();
        int m = baseStr.length();

        List<List<Integer>> adj = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int u = s1.charAt(i) - 'a';
            int v = s2.charAt(i) - 'a';
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int[] vis = new int[26];
            char minChar = dfs(baseStr.charAt(i) - 'a', vis, adj);
            res.append(minChar);
        }

        return res.toString();

    }
}
