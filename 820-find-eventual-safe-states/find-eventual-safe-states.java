class Solution {

    private boolean dfs(int node, List<List<Integer>> adj, int[] vis) {
        // vis[i] = 1 means i is vis and pathVisited
        vis[node] = 1;

        for (int it : adj.get(node)) {
            if (vis[it] == 0) {
                if (dfs(it, adj, vis))
                    return true;
            } else if (vis[it] == 1) {
                return true;
            }
        }

        // vis[i] = 2 means i is only vis not path Visited
        vis[node] = 2;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] adj) {
        int V = adj.length;

        // BFS
        // List<List<Integer>> adjRev = new ArrayList<>();
        // for (int i = 0; i < V; i++) {
        //     adjRev.add(new ArrayList<>());
        // }
        // int[] indegree = new int[V];
        // int[] safe = new int[V];

        // for (int i = 0; i < V; i++) {
        //     for (int it : adj[i]) {
        //         adjRev.get(it).add(i);
        //         indegree[i]++;
        //     }
        // }

        // List<Integer> topo = new ArrayList<>();

        // Queue<Integer> q = new LinkedList<Integer>();

        // for (int i = 0; i < V; i++) {
        //     if (indegree[i] == 0)
        //         q.add(i);
        // }

        // while (!q.isEmpty()) {
        //     int node = q.poll();
        //     // topo.add(node);
        //     safe[node] = 1;
        //     for (int it : adjRev.get(node)) {
        //         indegree[it]--;
        //         if (indegree[it] == 0)
        //             q.add(it);
        //     }
        // }

        // for (int i = 0; i < V; i++)
        //     if (safe[i] == 1)
        //         topo.add(i);
        // return topo;

        // DFS
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Form the adjacency matrix
        for (int i = 0; i < V; i++) {
            for (int it : adj[i]) {
                adjList.get(i).add(it);
            }
        }

        int[] vis = new int[V];
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, adjList, vis);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (vis[i] == 2)
                res.add(i);
        }

        return res;
    }
}