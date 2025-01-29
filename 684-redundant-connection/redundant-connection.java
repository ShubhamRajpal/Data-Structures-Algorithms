class Solution {

    private boolean dfs(int u, int target, List<List<Integer>> adjList, boolean[] vis) {
        vis[u] = true;
        if (u == target) return true;

        for (int adjNode : adjList.get(u)) {
            if (!vis[adjNode]) {
                if(dfs(adjNode, target, adjList, vis)) return true;;
            }
        }

        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            boolean[] vis = new boolean[n + 1];
            if (dfs(u, v, adjList, vis)) {
                return new int[] { u, v };
            }
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        System.out.print(adjList);

        return new int[] {};
    }
}