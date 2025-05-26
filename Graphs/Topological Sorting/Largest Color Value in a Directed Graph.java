/*
  Problem Link: https://leetcode.com/problems/largest-color-value-in-a-directed-graph/
*/

//Approach (Using Topological Sorting with BFS)
//T.C : O(V+E)
//S.C : O(V+E)
class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        int m = edges.length;
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        int[] indegree = new int[n];
        for (int i = 0; i < m; i++) {
            adjList.get(edges[i][0]).add(edges[i][1]);
            indegree[edges[i][1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        int[][] dp = new int[n][26];
        int totalNodes = 0;

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                dp[i][colors.charAt(i) - 'a']++;
            }
        }

        int answer = 1;

        while (!q.isEmpty()) {
            int u = q.peek();
            q.remove();
            totalNodes++;

            answer = Math.max(answer, dp[u][colors.charAt(u) - 'a']);

            for (int v : adjList.get(u)) {
                int color = colors.charAt(v) - 'a';

                for (int j = 0; j < 26; j++) {
                    int cnt = (color == j) ? 1 : 0;
                    dp[v][j] = Math.max(dp[v][j], dp[u][j] + cnt);
                }

                indegree[v]--;
                if (indegree[v] == 0) {
                    q.add(v);
                }
            }

        }

        if (totalNodes != n) {
            return -1;
        }

        return answer;

    }
}
