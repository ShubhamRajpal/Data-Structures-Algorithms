class Solution {

    private boolean bfs(int u, int v, List<List<Integer>> adjList, boolean[] vis){
        
        Queue<Integer> q = new LinkedList<>();
        q.add(u);
        vis[u] = true;

        while(!q.isEmpty()){
            int node = q.poll();

            if(node == v) return true;
            for(int adj : adjList.get(node)){
                if(!vis[adj]){
                    vis[adj] = true;
                    q.add(adj);
                }
            }
        }

        return false;

    }


    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {


        // Brute
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i = 0; i < prerequisites.length; i++){
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];
            adjList.get(u).add(v);
        }

        List<Boolean> ans = new ArrayList<>();
        for(int i = 0; i < queries.length; i++){
            int u = queries[i][0];
            int v = queries[i][1];
            boolean[] vis = new boolean[numCourses];
            boolean res = bfs(u, v, adjList, vis);
            ans.add(res);
        }

        return ans;
















        
        // List<List<Integer>> adjList = new ArrayList<>();
        // for(int i = 0; i < numCourses; i++){
        //     adjList.add(new ArrayList<>());
        // }

        // int indegree[] = new int[numCourses];
        // for(int i = 0; i < prerequisites.length; i++){
        //     int u = prerequisites[i][0];
        //     int v = prerequisites[i][1];
        //     adjList.get(u).add(v);
        //     indegree[v]++;
        // }

        // Queue<Integer> q = new LinkedList<>();
        // for(int i = 0; i < numCourses; i++){
        //     if(indegree[i] == 0) {
        //         q.offer(i);
        //     }
        // }

        // Map<Integer, Set<Integer>> prereqNodes = new HashMap<>();
        // while(!q.isEmpty()){
        //     int node = q.poll();

        //     // Since we want to get the prerequisites of this popped out node in O(1) time we stored this node in all the preprequisites of adjNode 
        //     for(int adjNode : adjList.get(node)){
                
        //         if(prereqNodes.get(adjNode) == null){
        //             Set<Integer> temp = new HashSet<>();
        //             temp.add(node);
        //             prereqNodes.put(adjNode, temp);
        //         }
        //         prereqNodes.get(adjNode).add(node);

        //         //  We also need to store prerequisistes of this node in adjNode as well because due to transtivity all nodes prerequisites of this node are also prerequisites of all the adjNode
        //         for(int curPrereq : prereqNodes.getOrDefault(node, new HashSet<>())){
        //             prereqNodes.get(adjNode).add(curPrereq);
        //         }

        //         indegree[adjNode]--;
        //         if(indegree[adjNode] == 0) q.offer(adjNode);
        //     }
        // }

        // List<Boolean> ans = new ArrayList<>();

        // for(int j = 0; j < queries.length; j++){
        //     int u = queries[j][0];
        //     int v = queries[j][1];

        //     if(prereqNodes.getOrDefault(v, new HashSet<>()).contains(u)){
        //         ans.add(true);
        //     }else ans.add(false);
        // }

        // return ans;

    }
}