class Solution {

    public boolean isCyclic(int node, Stack<Integer> st, ArrayList<ArrayList<Integer>> adj, int[] vis){
        vis[node] = 1;

        for(int it : adj.get(node)){
            if(vis[it] == 0){
                if(isCyclic(it, st, adj, vis)) return true;
            }else if(vis[it] == 1){ 
                return true;
            }
            
        }

        st.push(node);
        vis[node] = 2;
        return false;
    
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < prerequisites.length; i++){
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }


        // // BFS
        int ans[] = new int[numCourses];
        int[] indegree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++){
            indegree[prerequisites[i][0]]++; 
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0) q.add(i);
        }
        int cnt = 0, i= 0;
        while(!q.isEmpty()){
            int front = q.poll();
            cnt++;
            ans[i++] = front;

            for(int it : adj.get(front)){
                indegree[it]--;
                if(indegree[it] == 0) q.add(it);
            }
        }

        if(i == numCourses) return ans;
        return new int[0];

        // DFS
        Stack<Integer> st = new Stack<>();
        int[] vis = new int[numCourses];

        for(int i = 0;i < numCourses;i++){
            if(vis[i] == 0){
                if(isCyclic(i, st, adj, vis)){
                    return new int[0];
                }
            }
        }

        int[] res = new int[numCourses];
        int i = 0;
        while(!st.isEmpty()){
            res[i++] = st.pop();
        }

        return res;
        
    }
}
