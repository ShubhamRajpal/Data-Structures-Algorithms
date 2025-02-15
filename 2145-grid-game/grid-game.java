class Solution {
    public long gridGame(int[][] grid) {
        // int m = grid[0].length;
        // long firstRowRemainingSum = 0;
        // for(int col : grid[0]) firstRowRemainingSum += col;

        // long robot2MinimizedSum = Long.MAX_VALUE;
        // long secondRowRemainingSum = 0;

        // for(int r1Col = 0; r1Col < m; r1Col++){

        //     firstRowRemainingSum -= grid[0][r1Col];
        //     robot2MinimizedSum = Math.min(robot2MinimizedSum, Math.max(firstRowRemainingSum, secondRowRemainingSum));
        //     secondRowRemainingSum +=  grid[1][r1Col];
        // }

        // return robot2MinimizedSum;

        int n = grid[0].length;
        long firstRowRemainingSum = 0;
        for(int col : grid[0]){
            firstRowRemainingSum += col;
        }

        long secondRowRemainingSum = 0;
        long robot2MinimizedSum = Long.MAX_VALUE;
        for(int col = 0; col < n; col++){
            firstRowRemainingSum  -= grid[0][col];
            
            robot2MinimizedSum = Math.min(robot2MinimizedSum, Math.max(firstRowRemainingSum, secondRowRemainingSum));

            secondRowRemainingSum += grid[1][col];

        }

        return robot2MinimizedSum;

    }
}