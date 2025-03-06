/*
  Time Complexity: O(N^3)
  Space Complexity: O(N^2)
  Problem Link: https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/
*/

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        
        int[][] dist = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(dist[i], (int)1e9);
        for(int i = 0; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            dist[u][v] = wt;
            dist[v][u] = wt;
        }

        for(int i = 0 ; i < n; i++) dist[i][i] = 0;

        for(int via = 0; via < n; via++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(dist[i][via] == (int)1e9 || dist[via][j] == (int)1e9) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][via]+dist[via][j]);
                }
            }
        }


        int maxCity = n;
        int cityNo = -1;
        for(int i = 0; i < n; i++){
            int cnt = 0;
            for(int j = 0; j < n; j++){
                if(dist[i][j] <= distanceThreshold) cnt++;
            }

            if(cnt <= maxCity){
                maxCity = cnt;
                cityNo = i;
            }
        }

        return cityNo;
    }
}
