/*
    Time Complexity : O(N*M*4) Space Complexity: O(N*M*4)
*/

class Tuple {
    int first, second, distance;

    public Tuple(int d, int f, int s) {
        distance = d;
        first = f;
        second = s;
    }
}

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1;
        if (n == 1 && grid[0][0] == 0 && grid[n - 1][n - 1] == 0)
            return 1;

        int[][] dist = new int[n][n];
        Queue<Tuple> q = new LinkedList<Tuple>();
        q.add(new Tuple(1, 0, 0));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = (int) (1e9);
            }
        }

        dist[0][0] = 1;

        while (!q.isEmpty()) {
            Tuple t = q.peek();
            int dis = t.distance;
            int r = t.first;
            int c = t.second;
            q.remove();

            for (int delrow = -1; delrow <= 1; delrow++) {
                for (int delcol = -1; delcol <= 1; delcol++) {
                    int nrow = r + delrow;
                    int ncol = c + delcol;

                    if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n && grid[nrow][ncol] == 0 &&
                            dis + 1 < dist[nrow][ncol]) {
                        dist[nrow][ncol] = dis + 1;
                        if (nrow == n - 1 && ncol == n - 1)
                            return dis + 1;
                        q.add(new Tuple(dis + 1, nrow, ncol));
                    }
                }

            }
        }
        return -1;
    }
}