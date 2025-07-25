/*
  Problem Link: https://leetcode.com/problems/minimum-score-after-removals-on-a-tree/
*/

//Approach - Using DFS
//T.C : O(n^2)
//S.C : O(V+E), V = number of vertices and E = number of edges
class Solution {
    public void dfs(int node, int parent, int[] nums, int[] subtreeXor, int[] inTime, int[] outTime, int[] timer,
            List<List<Integer>> adj) {

        subtreeXor[node] = nums[node];
        inTime[node] = timer[0];
        timer[0]++;

        for (int adjNode : adj.get(node)) {
            if (adjNode != parent) {
                dfs(adjNode, node, nums, subtreeXor, inTime, outTime, timer, adj);
                subtreeXor[node] ^= subtreeXor[adjNode];
            }
        }

        outTime[node] = timer[0];
        timer[0]++;
    }

    public boolean isAncestor(int u, int v, int[] inTime, int[] outTime) {
        return inTime[u] <= inTime[v] && outTime[u] >= outTime[v];
    }

    public int getPairScore(int a, int b, int c) {
        int maxScore = Math.max(a, Math.max(b, c));
        int minScore = Math.min(a, Math.min(b, c));

        return maxScore - minScore;
    }

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int it[] : edges) {
            int u = it[0];
            int v = it[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] inTime = new int[n];
        int[] outTime = new int[n];

        int[] subtreeXor = new int[n];
        int[] timer = new int[1];
        int result = Integer.MAX_VALUE;
        dfs(0, -1, nums, subtreeXor, inTime, outTime, timer, adj);

        for (int u = 1; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                int xor1 = 0, xor2 = 0, xor3 = 0;

                if (isAncestor(u, v, inTime, outTime)) {
                    xor1 = subtreeXor[v];
                    xor2 = subtreeXor[u] ^ subtreeXor[v];
                    xor3 = subtreeXor[0] ^ xor1 ^ xor2;
                } else if (isAncestor(v, u, inTime, outTime)) {
                    xor1 = subtreeXor[u];
                    xor2 = subtreeXor[v] ^ subtreeXor[u];
                    xor3 = subtreeXor[0] ^ xor1 ^ xor2;
                } else {
                    xor1 = subtreeXor[u];
                    xor2 = subtreeXor[v];
                    xor3 = subtreeXor[0] ^ xor1 ^ xor2;
                }

                result = Math.min(result, getPairScore(xor1, xor2, xor3));
            }
        }

        return result;
    }
}
