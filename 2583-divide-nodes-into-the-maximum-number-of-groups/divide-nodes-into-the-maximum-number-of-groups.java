class Solution {

    private boolean isBipartite(int node, int n, List<List<Integer>> adjList, int[] color){
        
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        color[node] = 1;
        
        while(!q.isEmpty()){
            int curNode = q.poll();
    
            for(int adjNode : adjList.get(curNode)){
                if(color[adjNode] == -1){
                    color[adjNode] = 1 - color[curNode];
                    q.add(adjNode);
                }else if(color[curNode] == color[adjNode]){
                    return false;
                }
            }
        }
    
        return true;
    }

    private int bfs(int node, int n, List<List<Integer>> adjList){

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n + 1];
        q.add(node);
        vis[node] = true;
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curNode = q.poll(); 

                for(int it : adjList.get(curNode)){
                    if(!vis[it]){
                        vis[it] = true;
                        q.add(it);
                    }
                }
            }
            level++;
        }
        return level;
    }

    private int getMaxGroupsComponentWise(int node, List<List<Integer>> adjList, boolean[] vis, int[]maxGroups){
        vis[node] = true;
        int maxi = maxGroups[node];

        for(int it : adjList.get(node)){
            if(!vis[it]){
                maxi = Math.max(maxi, getMaxGroupsComponentWise(it, adjList, vis, maxGroups));
            }
        }

        return maxi;
    }

    public int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        // Construct the graph
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int it[] : edges) {
            adjList.get(it[0]).add(it[1]);
            adjList.get(it[1]).add(it[0]);
        }

        // Check if the graph is a bipartite or not
        int[] color = new int[n + 1];
        Arrays.fill(color, -1);
        for (int i = 1; i <= n; i++) {
            if (color[i] == -1) {
                if (isBipartite(i, n, adjList, color) == false) return -1;
            }
        }

        // Find max groups for each node
        int[] maxGroups = new int[n+1];
        for (int i = 1; i <= n; i++) {
             maxGroups[i] = bfs(i, n, adjList);
        }

        for(int i : maxGroups) System.out.print(i+" ");

        // Find max for each component
        int totalGroups = 0;
        boolean[] vis = new boolean[n+1];
        int maxi = 0;
        for(int i = 1; i <= n;i++){
            if(!vis[i]){
                vis[i] = true;
             maxi = getMaxGroupsComponentWise(i, adjList, vis, maxGroups);
                System.out.print(maxi);
                totalGroups += (maxi);
            }
        }
        
        return totalGroups;
    }
}