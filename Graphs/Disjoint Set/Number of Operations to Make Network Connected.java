/*
  Problem Link: https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/
*/


class DisjointSet {
    List<Integer> size, parent;

    public DisjointSet(int V) {
        size = new ArrayList<>();
        parent = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            size.add(1);
            parent.add(i);
        }
    }

    public int findUPar(int node) {
        if (parent.get(node) == node)
            return node;

        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void UnionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v)
            return;

        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

class Solution {
    public int makeConnected(int n, int[][] connections) {
        int cntExtraEdges = 0;
        DisjointSet ds = new DisjointSet(n);
        for (int i = 0; i < connections.length; i++) {
            int u = connections[i][0];
            int v = connections[i][1];

            if (ds.findUPar(u) == ds.findUPar(v))
                cntExtraEdges++;
            else
                ds.UnionBySize(u, v);

        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (ds.parent.get(i) == i)
                cnt++;
        }
        int ans = cnt - 1;
        if (cntExtraEdges >= ans)
            return ans;
        return -1;
    }
}
