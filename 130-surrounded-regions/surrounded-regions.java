class Solution {

    private void dfs(int n, int m, int row, int col, int[][] vis, char[][] board, int[] delRow, int[] delCol){
        vis[row][col] = 1;

        for(int i = 0; i < 4; i++){
            int nRow = row + delRow[i];
            int nCol = col + delCol[i];

            if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && board[nRow][nCol] == 'O' && vis[nRow][nCol] == 0){
                dfs(n, m, nRow, nCol, vis, board, delRow, delCol);
            }
        }
    }

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] vis = new int[n][m];
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};
        for(int j = 0; j < m; j++){
            if(board[0][j] == 'O' && vis[0][j] == 0){
                dfs(n, m, 0, j, vis, board, delRow, delCol);
            }

            if(board[n-1][j] == 'O' && vis[n-1][j] == 0){
                dfs(n, m, n-1, j, vis, board, delRow, delCol);
            }
        }


        for(int i = 0; i < n; i++){
            if(board[i][0] == 'O' && vis[i][0] == 0){
                dfs(n, m, i, 0, vis, board, delRow, delCol);
            }

            if(board[i][m-1] == 'O' && vis[i][m-1] == 0){
                dfs(n, m, i, m-1, vis, board, delRow, delCol);
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'O' && vis[i][j] == 0){
                    board[i][j] = 'X';
                }
            }
        }


    }
}