class Pair {
    int first, second;

    public Pair(int f, int s) {
        first = f;
        second = s;
    }
}

class Solution {

    private void bfs(int n, int m, int sr, int sc, int[] dx, int[] dy, int[][] res, int[][] image, int inColor, int newColor) {
        
        Queue<Pair> q = new LinkedList<>();
        res[sr][sc] = newColor;
        q.add(new Pair(sr, sc));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int row = p.first;
            int col = p.second;

            for (int i = 0; i <= 3; i++) {
                int nRow = row + dx[i];
                int nCol = col + dy[i];
                if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && image[nRow][nCol] == inColor && res[nRow][nCol] != newColor) {
                    res[nRow][nCol] = newColor;
                    q.add(new Pair(nRow, nCol));
                }
            }

        }
    }

    private void dfs(int n, int m, int r, int c, int[] dx, int[] dy, int[][] res, int[][] image, int inColor,
            int newColor) {
        res[r][c] = newColor;

        for (int i = 0; i < 4; i++) {
            int nrow = r + dx[i];
            int ncol = c + dy[i];

            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && image[nrow][ncol] == inColor
                    && res[nrow][ncol] != newColor) {
                res[nrow][ncol] = newColor;
                dfs(n, m, nrow, ncol, dx, dy, res, image, inColor, newColor);
            }
        }

    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;

        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = image[i][j];
            }
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        // dfs(n, m, sr, sc, dx, dy, res, image, image[sr][sc], color);
        bfs(n, m, sr, sc, dx, dy, res, image, image[sr][sc], color);
        return res;

    }
}