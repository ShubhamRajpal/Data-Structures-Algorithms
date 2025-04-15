/*
Problem Link : https://leetcode.com/problems/critical-connections-in-a-network/
T.C: O(V+2E)
S.C: O(V+2E) + O(3V)
*/
class Solution {
    public int timer = 1;

    public void dfs(int node, int parent, int[] vis, int[] low, int[] tin, List<List<Integer>> adj, List<List<Integer>> bridges){
        vis[node] = 1;
        low[node] = tin[node] = timer;
        timer++;

        for(int it : adj.get(node)){
            if(it == parent) continue;
            if(vis[it] == 0){
                dfs(it, node, vis, low, tin, adj, bridges);
                low[node] = Math.min(low[it], low[node]);
                if(low[it] > tin[node]){
                    bridges.add(Arrays.asList(it, node));
                }
            }else{
                low[node] = Math.min(low[it], low[node]);
            }
        }
    } 
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
    
        int V = connections.size();
        List<List<Integer>> adj = new ArrayList<>();
        
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < V; i++){
            int u = connections.get(i).get(0);
            int v = connections.get(i).get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];

        List<List<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, vis, low, tin, adj, bridges);
        return bridges;


    }
}
