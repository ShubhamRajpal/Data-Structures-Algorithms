/*
  Problem Link: https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii
*/

//Approach (Using BFS and Marking Nodes)
//T.C : O(V+E)
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

    private int[] bfs(int node, List<List<Integer>> adjList, int n) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        int[] level = new int[n];
        q.add(new int[] { node, 0 });
        vis[node] = true;

        while (!q.isEmpty()) {
            int cur = q.peek()[0];
            int dist = q.peek()[1];
            q.poll();

            level[cur] = (dist % 2 == 0) ? 0 : 1;

            for (int adjNode : adjList.get(cur)) {
                if (!vis[adjNode]) {
                    vis[adjNode] = true;
                    q.add(new int[] { adjNode, dist + 1 });
                }
            }
        }

        return level;
    }

    private int[] findLevel(List<List<Integer>> adjList) {
        int n = adjList.size();
        int[] res = bfs(0, adjList, n);
        return res;

    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int N = edges1.length + 1;
        int M = edges2.length + 1;

        List<List<Integer>> adjList1 = buildGraph(edges1, N);
        List<List<Integer>> adjList2 = buildGraph(edges2, M);

        int[] result1 = findLevel(adjList1);
        int[] result2 = findLevel(adjList2);

        int countOddLevelNodesTree2 = Math.toIntExact(Arrays.stream(result2).filter(n -> n == 0).count());
        int countMaxTargetNodes = Math.max(countOddLevelNodesTree2, M - countOddLevelNodesTree2);

        int countOddLevelNodesTree1 = Math.toIntExact(Arrays.stream(result1).filter(n -> n == 0).count());
        int countEvenLevelNodesTree1 = N - countOddLevelNodesTree1;

        for (int i = 0; i < result1.length; i++) {
            result1[i] = (result1[i] == 0 ? countOddLevelNodesTree1 : countEvenLevelNodesTree1) + countMaxTargetNodes;
        }

        return result1;
    }
}
