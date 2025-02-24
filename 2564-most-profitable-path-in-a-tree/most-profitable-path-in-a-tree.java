class Solution {
    private boolean dfsBob(int src, int time, Map<Integer, Integer> bobTimeMap, List<List<Integer>> adj, boolean[] vis){
        
        bobTimeMap.put(src, time);
        vis[src] = true;

        if(src == 0){
            return true;
        }

        for(int it : adj.get(src)){
            if(!vis[it]){
                if(dfsBob(it, time+1, bobTimeMap, adj, vis)){
                    return true;
                }
            }
        }

        bobTimeMap.remove(src);
        return false;
    }

    private void dfsAlice(int node, int time, int income, boolean[] vis, List<List<Integer>> adj, int[] amount, Map<Integer, Integer> bobTimeMap, int[] res){

        vis[node] = true;
        if(!bobTimeMap.containsKey(node) || time < bobTimeMap.get(node)){
            income += amount[node];
        }else if(bobTimeMap.get(node) == time){
            income += amount[node]/2;
        }
        

        if(adj.get(node).size() == 1 && node != 0){
            res[0] = Math.max(res[0], income);
            return;
        }


       

        for(int it : adj.get(node)){
            if(!vis[it]){
                dfsAlice(it, time+1, income, vis, adj, amount, bobTimeMap, res);
            }
        }
    }


    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;

        // Build the graph from edges
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Perform DFS for finding Bob's time to reach each node on its path to node 0
        Map<Integer, Integer> bobTimeMap = new HashMap<>();
        boolean[] vis = new boolean[n];
        dfsBob(bob, 0, bobTimeMap, adj, vis);
        Arrays.fill(vis, false);


        //Perform DFS for Alice to find the maximum income to reach a leaf node
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        dfsAlice(0, 0, 0, vis, adj, amount, bobTimeMap, res);
        return res[0];
    }
}