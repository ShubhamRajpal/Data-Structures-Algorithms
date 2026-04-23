/*
  Problem Link: https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/
*/

//Approach - (DSU + map)
//T.C : O(n + m*alpha(n)), alpha(n) = inverse Ackermann function.
//S.C : O(n)
class DisjointSet {
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v)
            return;
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }

}

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        DisjointSet ds = new DisjointSet(n - 1);
        for (int[] edge : allowedSwaps) {
            int u = edge[0];
            int v = edge[1];

            ds.unionBySize(u, v);
        }

        Map<Integer, List<Integer>> groupMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int parent = ds.findUPar(i);
            groupMap.computeIfAbsent(parent, k -> new ArrayList<>()).add(i);
        }

        int hammingDistance = 0;

        for (List<Integer> group : groupMap.values()) {

            Map<Integer, Integer> freq = new HashMap<>();
            for (int idx : group) {
                int node = source[idx];
                freq.put(node, freq.getOrDefault(node, 0) + 1);
            }

            for (int idx : group) {
                int targetNode = target[idx];
                if (freq.getOrDefault(targetNode, 0) > 0) {
                    freq.put(targetNode, freq.getOrDefault(targetNode, 0) - 1);
                } else {
                    hammingDistance++;
                }
            }

        }

        return cnt;
    }
}
