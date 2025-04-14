
/*
  T.C : O(V+E), V = # vertices, E = # edges , 
  S.C : O(V)
  Problem Link: https://practice.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1
*/

class Solution
{
    public void dfs1(int node, Stack<Integer> st, int[] vis, ArrayList<ArrayList<Integer>> adj){
        
        vis[node] = 1;
        for(int it : adj.get(node)){
            if(vis[it] == 0){
                dfs1(it, st, vis, adj);
            }
        }
        
        st.push(node);
    }
    
     public void dfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adjT){
        
        vis[node] = 1;
        for(int it : adjT.get(node)){
            if(vis[it] == 0){
                dfs(it, vis, adjT);
            }
        }
        
    }
    
    //Function to find number of strongly connected components in the graph.
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
    {
        //code here
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<Integer>();
        
        //Step1 
        for(int i = 0; i < V; i++){
            if(vis[i] == 0){
                dfs1(i, st, vis, adj);
            }
        }
        
        //Step2
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        
        for(int i = 0; i < V; i++){
            adjT.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < V; i++){
            for(int it : adj.get(i)){
                adjT.get(it).add(i);
            }
        }
        
        
        //Step3
        Arrays.fill(vis, 0);
        int scc = 0;
        
        while(!st.isEmpty()){
            int i = st.pop(); 
            if(vis[i] == 0){
                scc++;
                dfs(i, vis, adjT);
            }
        }
        
        return scc;
    }
}
