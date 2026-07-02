/*
  Problem Link: https://leetcode.com/problems/find-a-safe-walk-through-a-grid
*/

//Appoach-1 Using Dijkstra's
//T.C : O(E · log V) = O(m·n · log(m·n))
//S.C : O(m*n)
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();

        // {cost, r, c}, min-heap ordered by cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[][] result = new int[m][n];
        for (int[] row : result) Arrays.fill(row, Integer.MAX_VALUE);

        result[0][0] = grid.get(0).get(0); //Source = {0, 0}
        pq.offer(new int[]{result[0][0], 0, 0});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], r = top[1], c = top[2];

            if (d > result[r][c]) continue;

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                if (d + grid.get(nr).get(nc) < result[nr][nc]) {
                    result[nr][nc] = d + grid.get(nr).get(nc);
                    pq.offer(new int[]{result[nr][nc], nr, nc});
                }
            }
        }

        return health - result[m-1][n-1] >= 1;
    }
}

//Appoach-2 (Using 0-1 BFS)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        Deque<int[]> q = new ArrayDeque<>();
        int[][] dis = new int[m][n];

        for (int[] it : dis) {
            Arrays.fill(it, Integer.MAX_VALUE);
        }

        dis[0][0] = grid.get(0).get(0);
        q.offerFirst(new int[] { 0, 0 });

        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            for (int dir = 0; dir < 4; dir++) {
                int newr = r + dx[dir];
                int newc = c + dy[dir];
                if (newr >= 0 && newr < m && newc >= 0 && newc < n
                        && (dis[r][c] + grid.get(newr).get(newc) < health)) {

                    if (dis[newr][newc] > dis[r][c] + grid.get(newr).get(newc)) {
                        dis[newr][newc] = dis[r][c] + grid.get(newr).get(newc);

                        if (grid.get(newr).get(newc) == 1)
                            q.offerLast(new int[] { newr, newc });
                        else
                            q.offerFirst(new int[] { newr, newc });
                    }
                }
            }
        }

        return false;
    }
}
