/*
  Problem Link: https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i
*/

//Approach-1 (Using BFS)
//T.C : O(V*(V+E))
//S.C : O(V+E)
class Solution {

    private List<List<Integer>> buildGraph(int[][] edges, int N) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }

        return adjList;
    }

    private int[] findCount(List<List<Integer>> adjList, int count) {
        int n = adjList.size();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = bfs(i, adjList, count);
        }

        return result;
    }

    private int bfs(int node, List<List<Integer>> adjList, int d) {
        int n = adjList.size();
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { node, 0 });
        boolean[] visited = new boolean[n];
        visited[node] = true;
        int totalNodes = 0;

        while (!q.isEmpty()) {
            int cur = q.peek()[0];
            int dist = q.peek()[1];
            q.poll();

            if (dist > d) continue;
            totalNodes++;

            for (int adjNode : adjList.get(cur)) {
                if (!visited[adjNode]) {
                    q.add(new int[] { adjNode, dist + 1 });
                    visited[adjNode] = true;
                }
            }

        }

        return totalNodes;

    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int N = edges1.length + 1;
        int M = edges2.length + 1;

        List<List<Integer>> adjList1 = buildGraph(edges1, N);
        List<List<Integer>> adjList2 = buildGraph(edges2, M);

        int[] result1 = findCount(adjList1, k);
        int[] result2 = findCount(adjList2, k - 1);

        int maxTargetNodes = Arrays.stream(result2).max().getAsInt();

        for (int i = 0; i < result1.length; i++) {
            result1[i] += maxTargetNodes;
        }

        return result1;
    }
}


//Approach-2 (Using DFS)
//T.C : O(V*(V+E))
//S.C : O(V+E)
class Solution {

    private List<List<Integer>> buildGraph(int[][] edges, int N) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }

        return adjList;
    }

    private int[] findCount(List<List<Integer>> adjList, int count) {
        int n = adjList.size();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            result[i] = dfs(i, adjList, count, visited);
        }

        return result;
    }


    private int dfs(int node, List<List<Integer>> adjList, int d, boolean[] visited) {
        visited[node] = true;
        if (d < 0) {
            return 0;
        }

        int countNodes = 1;

        for (int adjNode : adjList.get(node)) {
            if (!visited[adjNode]) {
                countNodes += dfs(adjNode, adjList, d - 1, visited);
            }
        }

        return countNodes;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int N = edges1.length + 1;
        int M = edges2.length + 1;

        List<List<Integer>> adjList1 = buildGraph(edges1, N);
        List<List<Integer>> adjList2 = buildGraph(edges2, M);

        int[] result1 = findCount(adjList1, k);
        int[] result2 = findCount(adjList2, k - 1);

        int maxTargetNodes = Arrays.stream(result2).max().getAsInt();

        for (int i = 0; i < result1.length; i++) {
            result1[i] += maxTargetNodes;
        }

        return result1;
    }
}
