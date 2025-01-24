class Solution {
    public List<Integer> eventualSafeNodes(int[][] adj) {
        int V = adj.length;
        List<List<Integer>> adjRev = new ArrayList<>();
        for(int  i = 0; i < V; i++){
            adjRev.add(new ArrayList<>());
        }
        int[] indegree = new int[V];
        int[] safe = new int[V];
        
        for(int i = 0; i < V; i++){
            for(int it : adj[i]){
                adjRev.get(it).add(i);
                indegree[i]++;
            }
        }
        
        List<Integer> topo = new ArrayList<>();
        
        Queue<Integer> q = new LinkedList<Integer>();
        
        for(int i = 0; i < V; i++){
            if(indegree[i] == 0) q.add(i);
        }
        
        while(!q.isEmpty()){
            int node = q.poll();
            // topo.add(node);
            safe[node] = 1;
            for(int it : adjRev.get(node)){
                indegree[it]--;
                if(indegree[it] == 0) q.add(it);
            }
        }
        
        
        for(int i = 0; i < V; i++) if(safe[i] == 1) topo.add(i);
        return topo;
        
    }
}