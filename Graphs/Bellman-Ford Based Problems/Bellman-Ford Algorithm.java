/*
Time Complexity: O(V*E), where V = no. of vertices and E = no. of Edges.
Space Complexity: O(V) for the distance array which stores the minimized distances.
Problem Link: https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1

*   edges: vector of vectors which represents the graph
*   S: source vertex to start traversing graph with
*   V: number of vertices
*/
class Solution {
    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        // Write your code here
        int[] dist = new int[V];
        Arrays.fill(dist, (int)1e8);
        dist[S] = 0;
        
        for(int i = 0; i < V; i++){
            for(ArrayList<Integer> it : edges){
                int u = it.get(0);
                int v = it.get(1);
                int wt = it.get(2);{
                    if(dist[u] != 1e8 && dist[u] + wt < dist[v]){
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }

        // Do one more iteration to detect negative cycle
        for(ArrayList<Integer> it : edges){
            int u = it.get(0);
            int v = it.get(1);
            int wt = it.get(2);{
                if(dist[u] != 1e8 && dist[u] + wt < dist[v]){
                    int[] temp = new int[1];
                    temp[0] = -1;
                    return temp;
                }
            }
        }
        
        return dist;
        
    }
}
