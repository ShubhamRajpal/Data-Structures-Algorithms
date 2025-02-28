/* 
GfG Link: https://practice.geeksforgeeks.org/problems/shortest-path-in-weighted-undirected-graph/1              
*/

class Pair{
    int distance, node;
    public Pair(int d, int n){
        distance = d;
        node = n;
    }
}

class Solution {
    public List<Integer> shortestPath(int n, int m, int edges[][]) {
        //  Code Here.
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            adj.add(new ArrayList<>());
        }
        
        for(int i = 0; i < m; i++){
            adj.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0], edges[i][2]));
        }
        
        
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a,b) -> a.distance - b.distance);
        
        int[] dist = new int[n+1];
        int[] parent = new int[n+1];
        
        for(int i = 1; i <= n; i++){
            dist[i] = (int)(1e9);
            parent[i] = i;
        }
        dist[1] = 0;
        
        pq.add(new Pair(0, 1));
        
        while(!pq.isEmpty()){
            
            Pair p = pq.peek();
            int dis = p.distance;
            int nd = p.node;
            pq.remove();
            
            for(Pair itr : adj.get(nd)){
                int adjNode = itr.distance;
                int edgeWeight = itr.node;
                
                if(dis + edgeWeight < dist[adjNode]){
                    dist[adjNode] = dis + edgeWeight;
                    parent[adjNode] = nd;
                    pq.add(new Pair(dist[adjNode], adjNode));
                    
                }
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        if(dist[n] == 1e9){
            ans.add(-1);
            return ans;
        }
        
        int node = n;
        while(parent[node] != node){
            ans.add(node);
            node = parent[node];
        }
        ans.add(1);
        ans.add(dist[n]);
        Collections.reverse(ans);
        return ans;
        
    }
}
