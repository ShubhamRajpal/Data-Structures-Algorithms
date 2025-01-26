class Pair{
    int first, second;
    public Pair(int f, int s){
        first = f;
        second = s;
    }
}

class Solution {

    private int bfs(int startNode, Set<Integer> visNodes, List<List<Integer>> adjList){
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(startNode, 0));
        int maxDistance = 0;
        while(!q.isEmpty()){
            Pair p = q.poll();
            int cur = p.first;
            int dis = p.second;
            for(int adjNode : adjList.get(cur)){
                if(!visNodes.contains(adjNode)){
                    q.offer(new Pair(adjNode, dis+1));
                    visNodes.add(adjNode);
                    maxDistance = Math.max(maxDistance, dis+1);
                }
            }
        }

        return maxDistance;
    }
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        List<List<Integer>> adjList = new ArrayList<>();

        // Create the adjacency list
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<>());
        }

        for(int i = 0; i < n; i++){
            // Add the reversed edges so that we can find the pathlength after traversal
            adjList.get(favorite[i]).add(i);
        }

        int longestCycle = 0;
        int longest2LengthCycle = 0;
        boolean[] vis = new boolean[n];

        // find cycles in graph
        for(int i = 0 ; i < n; i++){
            if(!vis[i]){
                Map<Integer, Integer> mp = new HashMap<>();
                int curNode = i;
                int curNodeCount = 0;
                // We keep going until we get the cycle
                while(!vis[curNode]){
                    vis[curNode] = true;
                    mp.put(curNode, curNodeCount);
                    int nextNode = favorite[curNode];
                    curNodeCount += 1;

                    // Check if there is a cycle endig at this nextNode
                    if(mp.containsKey(nextNode)){
                        int cycleLength = curNodeCount - mp.get(nextNode);
                        longestCycle = Math.max(longestCycle, cycleLength);

                        if(cycleLength == 2){
                            Set<Integer> visNodes = new HashSet<>();
                            visNodes.add(curNode);
                            visNodes.add(nextNode);
                            longest2LengthCycle += 2 + bfs(curNode, visNodes, adjList) + bfs(nextNode, visNodes, adjList);
                        }
                        break;
                    }

                    curNode = nextNode;


                }
            }
        }
        return Math.max(longestCycle, longest2LengthCycle);
    }
}