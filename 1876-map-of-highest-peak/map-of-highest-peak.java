// Similar to 0/1 Matrix Problem

class Triplet {
    int distance,  first,  second;
    public Triplet(int d, int f, int s){
        distance = d;
        first = f;
        second = s;
    }
}
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int n = isWater.length;
        int m = isWater[0].length;

        int[][] vis = new int[n][m];
        int[][] dis = new int[n][m];
        Queue<Triplet> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(isWater[i][j] == 1){
                    vis[i][j] = 1;
                    q.add(new Triplet(0, i, j));
                }
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!q.isEmpty()){
            int dist = q.peek().distance;
            int row = q.peek().first;
            int col = q.peek().second;
            q.poll();

            for(int k = 0; k < 4; k++){
                int nrow = row + dx[k];
                int ncol = col + dy[k];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0){
                    vis[nrow][ncol] = 1;
                    dis[nrow][ncol] = 1+dist;
                    q.add(new Triplet(1+dist, nrow, ncol));
                }
            }
        }

        return dis;
    }
}