/*
  Problem Link: https://leetcode.com/problems/minimum-cost-walk-in-weighted-graph/?envType=daily-question&envId=2025-03-20
*/

//T.C : 
/*
    The time complexity of the union-find operations (with path compression) is approximately O(α(n)), 
    where α is the inverse Ackermann function, which is practically constant for all reasonable values of n.
    The time complexity of processing each edge and each query is O(1).
    Therefore, the overall time complexity of the algorithm is O(E + Qα(n)), where E is the number of edges, 
    Q is the number of queries, and α(n) is the inverse Ackermann function.
*/
//S.C : O(N)
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
        if (ulp_u == ulp_v) return;

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
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        DisjointSet ds = new DisjointSet(n);
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            ds.unionBySize(u,v);
        }

        for(int[] edge : edges){
            int u = edge[0];
            int wt = edge[2];
            int par = ds.findUPar(u);
            cost[par] &= wt;
        }

        int[] res = new int[query.length];
        int k = 0;
        for(int[] q : query){
            int s = q[0];
            int t = q[1];

            if(ds.findUPar(s) != ds.findUPar(t)){
                res[k++] = -1;
                continue;
            }

            res[k++] = cost[ds.findUPar(s)];
        }

        return res;
    }
}
