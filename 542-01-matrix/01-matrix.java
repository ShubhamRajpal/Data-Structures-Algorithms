class Pair {
    int first, second;

    public Pair(int f, int s){
        first = f;
        second = s;
    }
}

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] distance = new int[n][m];

        Queue<Pair> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Add all souce cells with value '0'
                if (mat[i][j] == 0) {
                    q.add(new Pair(i, j));
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = -1;
                }
            }
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        while (!q.isEmpty()) {
            int row = q.peek().first;
            int col = q.peek().second;
            int dist = distance[row][col];
            q.poll();

            for (int i = 0; i < 4; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];

                if (nrow >= 0 && nrow < n & ncol >= 0 && ncol < m && distance[nrow][ncol] == -1) {
                    q.add(new Pair(nrow, ncol));
                    distance[nrow][ncol] = dist + 1;
                }
            }
        }

        return distance;

    }
}