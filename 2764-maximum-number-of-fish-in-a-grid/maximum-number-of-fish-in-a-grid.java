class Solution {

    private int dfs(int i, int j, int n, int m, int[][] grid){

        int totalFishCount = grid[i][j];
        grid[i][j] = 0;

        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        for(int it=0;it<4;it++){
            int nrow = i+dx[it];
            int ncol = j+dy[it];

            if(nrow < 0 || nrow >= n || ncol < 0 || ncol >= m || grid[nrow][ncol] == 0) continue;
            totalFishCount += dfs(nrow, ncol, n, m, grid);
        }

        return totalFishCount;
    }
    public int findMaxFish(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int maxFishCount = 0;
        for(int i = 0; i < n; i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]>0){
                    maxFishCount = Math.max(maxFishCount, dfs(i,j,n,m,grid));
                }
            }
        }

        return maxFishCount;
    }
}