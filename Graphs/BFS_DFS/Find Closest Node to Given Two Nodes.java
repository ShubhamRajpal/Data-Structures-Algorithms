/*
  Problem Link: https://leetcode.com/problems/find-closest-node-to-given-two-nodes/
*/

//Approach-1 (BFS)
//T.C : O(V+E), V = number of vertices and E = number of edges
//S.C : O(n)
class Solution {

    private void bfs(int node, int[] dist, int[] edges) {
        int n = edges.length;
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[n];
        dist[node] = 0;
        q.add(node);
        visited[node] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            int adjNode = edges[cur];

            if (adjNode != -1 && visited[adjNode] == 0) {
                dist[adjNode] = 1 + dist[cur];
                q.add(adjNode);
                visited[adjNode] = 1;
            }
        }

    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;

        int[] dist1 = new int[n];
        Arrays.fill(dist1, Integer.MAX_VALUE);

        int[] dist2 = new int[n];
        Arrays.fill(dist2, Integer.MAX_VALUE);

        bfs(node1, dist1, edges);
        bfs(node2, dist2, edges);

        int minDistNode = -1;
        int minDistUptoNow = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int maxD = Math.max(dist1[i], dist2[i]);

            if (minDistUptoNow > maxD) {
                minDistUptoNow = maxD;
                minDistNode = i;
            }
        }

        return minDistNode;
    }
}

//Approach-1 (DFS)
//T.C : O(V+E), V = number of vertices and E = number of edges
//S.C : O(n)
class Solution {
    private void dfs(int node, int[] dist, int[] edges, int[] visited) {
        visited[node] = 1;

        int adjNode = edges[node];
        if (adjNode != -1 && visited[adjNode] == 0) {
            dist[adjNode] = 1 + dist[node];
            dfs(adjNode, dist, edges, visited);
        }
    }


    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;

        int[] dist1 = new int[n];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        dist1[node1] = 0;
        int[] visited1 = new int[n];

        int[] dist2 = new int[n];
        Arrays.fill(dist2, Integer.MAX_VALUE);
        dist2[node2] = 0;
        int[] visited2 = new int[n];

        dfs(node1, dist1, edges, visited1);
        dfs(node2, dist2, edges, visited2);

        int minDistNode = -1;
        int minDistUptoNow = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int maxD = Math.max(dist1[i], dist2[i]);

            if (minDistUptoNow > maxD) {
                minDistUptoNow = maxD;
                minDistNode = i;
            }
        }

        return minDistNode;
    }
}
