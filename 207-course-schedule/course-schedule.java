class Solution {

    public boolean isCyclic(int node, ArrayList<ArrayList<Integer>> adj, int[] vis){
        vis[node] = 1;

        for(int it : adj.get(node)){
            if(vis[it] == 0){
                if(isCyclic(it, adj, vis)) return true;
            }else if(vis[it] == 1){ 
                return true;
            }
            
        }

        vis[node] = 2;
        return false;
        

    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < prerequisites.length; i++){
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }


        // BFS
        // int[] indegree = new int[numCourses];
        // for(int i = 0; i < prerequisites.length; i++){
        //     indegree[prerequisites[i][1]]++; 
        // }

        // Queue<Integer> q = new LinkedList<>();
        // for(int i = 0; i < numCourses; i++){
        //     if(indegree[i] == 0) q.add(i);
        // }
        // int cnt = 0;
        // while(!q.isEmpty()){
        //     int front = q.poll();
        //     cnt++;

        //     for(int it : adj.get(front)){
        //         indegree[it]--;
        //         if(indegree[it] == 0) q.add(it);
        //     }
        // }

        // if(cnt == numCourses) return true;
        // return false;

        // DFS
        int[] vis = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            if(vis[i] == 0){
                if(isCyclic(i, adj, vis)){
                    return false;
                }
            }
        }

        return true;
    }
}