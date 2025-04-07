
/*
  Problem Link: https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/
  T.C. - O(N) we only ttraversed the given array of stones and union operation takes constant time
  S.C. - 2 * O(Max Row of stones * Max Column of stones) for parent and size arrays in DSU
*/
class DisjointSet {
    List<Integer> size, rank, parent;

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
    public int removeStones(int[][] stones) {
        int n = stones.length;
        int maxRow = 0, maxCol = 0;
        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1);
        for (int i = 0; i < n; i++) {
            int nRow = stones[i][0];
            int nCol = stones[i][1] + maxRow + 1;
            ds.UnionBySize(nRow, nCol);
        }

        int cnt = 0;
        for (int i = 0; i < maxRow + maxCol + 1; i++) {
            if (ds.parent.get(i) == i && ds.size.get(i) > 1)
                cnt++;
        }

        return n - cnt;
    }
}
