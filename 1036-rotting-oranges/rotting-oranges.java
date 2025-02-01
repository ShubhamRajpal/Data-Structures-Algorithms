class Pair{
    int first, second, time;

    public Pair(int _first, int _second, int _time){
        first = _first;
        second = _second;
        time = _time;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) return -1;
        int n = grid.length;
        int m = grid[0].length;
        Queue<Pair> q = new LinkedList<Pair>();
        int vis[][] = new int[n][m];
        int cntFresh = 0;
        for(int  i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2){
                    q.add(new Pair(i, j, 0));
                    vis[i][j] = 2;
                }else vis[i][j] = 0;

                if(grid[i][j] == 1) cntFresh++;    
            }
        }

        int cnt = 0;
        int maxTime = 0;
        int delRow[] = {-1, 0, 1, 0};
        int delCol[] = {0, 1, 0, -1};
        while(!q.isEmpty()){

            int r = q.peek().first;
            int c = q.peek().second;
            int t = q.peek().time;
            q.poll();

            for(int  i = 0; i < 4; i++){
                int nRow = r + delRow[i];
                int nCol = c + delCol[i];

                if(nRow >= 0 && nRow < n && nCol >=0 && nCol < m && grid[nRow][nCol] == 1 && vis[nRow][nCol] != 2){
                    vis[nRow][nCol] = 2;
                    q.add(new Pair(nRow, nCol, t+1));
                    cnt++;
                }
            }

            maxTime = Math.max(maxTime, t);
        }
        if(cntFresh != cnt) return -1;
        return maxTime;

    }
}