/*
  Problem Link: https://leetcode.com/problems/minimum-height-trees/
*/


// Approach (using BFS and topo sort for finding central nodes)
// T.C: O(V+E)
// S.C: O(V+E)

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        int[] indegree = new int[n];
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            adjList.get(u).add(v);
            adjList.get(v).add(u);
            indegree[u]++;
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1) {
                q.add(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        while (n > 2) {

            int size = q.size();
            n -= size;
            while (size > 0) {
                int front = q.poll();

                for (int adjnbr : adjList.get(front)) {

                    indegree[adjnbr]--;
                    if (indegree[adjnbr] == 1) {
                        q.add(adjnbr);
                    }

                }

                size--;
            }
        }

        while (!q.isEmpty()) {
            ans.add(q.poll());
        }

        return ans;

    }
}
