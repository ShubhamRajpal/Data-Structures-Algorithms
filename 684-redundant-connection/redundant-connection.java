class DisjointSet{
    List<Integer> size, rank, parent;
    
    public DisjointSet(int V){
        size = new ArrayList<>();
        rank = new ArrayList<>();
        parent = new ArrayList<>();
        for(int i = 0; i < V+1; i++){
            size.add(1);
            rank.add(0);
            parent.add(i);
        }    
    }
    
    public int findUPar(int node){
        if(parent.get(node) == node) return node;
        
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }
    
    public void UnionByRank(int u, int v){
        int ulp_u = parent.get(u);
        int ulp_v = parent.get(v);
        if(ulp_u == ulp_v) return;
        
        if(rank.get(ulp_u) < rank.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
        }   
        else if(rank.get(ulp_v) < rank.get(ulp_u)){
            parent.set(ulp_v, ulp_u);
        }else{
            parent.set(ulp_u, ulp_v);
            rank.set(ulp_v, rank.get(ulp_v) + 1);
        }   
    }
    
    
    public boolean UnionBySize(int u, int v){
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if(ulp_u == ulp_v) return false;
        
        if(size.get(ulp_u) < size.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u)+size.get(ulp_v));
        }else{
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u)+size.get(ulp_v));
        }
        return true;
    }
}


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
        // List<List<Integer>> adjList = new ArrayList<>();

        // for (int i = 0; i <= n; i++) {
        //     adjList.add(new ArrayList<>());
        // }

        // for (int[] edge : edges) {
        //     int u = edge[0];
        //     int v = edge[1];
        //     boolean[] vis = new boolean[n + 1];
        //     if (dfs(u, v, adjList, vis)) {
        //         return new int[] { u, v };
        //     }
        //     adjList.get(u).add(v);
        //     adjList.get(v).add(u);
        // }


        // return new int[] {};


        // Optimal
        DisjointSet ds = new DisjointSet(n);
        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];

            if(!ds.UnionBySize(u,v)){
                return new int[]{u,v};
            }
        }

        return new int[]{};
    }
}