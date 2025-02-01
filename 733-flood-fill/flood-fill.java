class Solution {

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

        dfs(n, m, sr, sc, dx, dy, res, image, image[sr][sc], color);

        return res;

    }
}