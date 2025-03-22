// Problem Link: https://leetcode.com/problems/count-the-number-of-complete-components/?envType=daily-question&envId=2025-03-22

// Approach-1 (Using DFS)
// T.C : O(V+E)
// S.C : O(V+E)
class Solution {

    private void dfs(int node, boolean[] vis, int[] counts, Map<Integer, List<Integer>> adj) {
        vis[node] = true;
        counts[0]++;
        counts[1] += adj.getOrDefault(node, new ArrayList<>()).size();

        for (int adjNode : adj.getOrDefault(node, new ArrayList<>())) {
            if (!vis[adjNode]) {
                dfs(adjNode, vis, counts, adj);
            }
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {

        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int cnt = 0;
        int[] counts = new int[2];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(counts, 0);
            if (!vis[i]) {
                dfs(i, vis, counts, adj);

                int v = counts[0];
                int e = counts[1] / 2;
                int edgeLimit = v * (v - 1) / 2;

                if (e == edgeLimit)
                    cnt++;
            }

        }

        return cnt;
    }
}

// Approach-2 (Using BFS)
// T.C : O(V+E)
// S.C : O(V+E)
class Solution {

    private void bfs(int node, boolean[] vis, int[] counts, Map<Integer, List<Integer>> adj) {

        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        vis[node] = true;

        while (!q.isEmpty()) {

            int curNode = q.poll();
            counts[0]++;
            counts[1] += adj.getOrDefault(curNode, new ArrayList<>()).size();

            for (int adjNode : adj.getOrDefault(curNode, new ArrayList<>())) {
                if (!vis[adjNode]) {
                    vis[adjNode] = true;
                    q.add(adjNode);    
                }
            }
        }

    }

    public int countCompleteComponents(int n, int[][] edges) {

        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int cnt = 0;
        int[] counts = new int[2];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(counts, 0);
            if (!vis[i]) {
                bfs(i, vis, counts, adj);

                int v = counts[0];
                int e = counts[1] / 2;
                int edgeLimit = v * (v - 1) / 2;

                if (e == edgeLimit) cnt++;
            }
        }

        return cnt;
    }
}

// Approach-3 (Using DisjointSet)
