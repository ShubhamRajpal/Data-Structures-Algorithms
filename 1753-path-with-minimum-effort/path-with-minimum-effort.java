/*
    Time Complexity: O(4*N*M) log(4*N*M)
    Problem Link: https://leetcode.com/problems/path-with-minimum-effort/
*/


class Tuple{
    int row, col, difference;
    public Tuple(int d, int r, int c){
        difference = d;
        row = r;
        col = c;
    }
}

class Solution {
    public int minimumEffortPath(int[][] heights) {
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>((a, b) -> a.difference - b.difference);
        int n = heights.length;
        int m = heights[0].length;
        int diff[][] = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                diff[i][j] = (int)1e9;
            }
        }

        diff[0][0] = 0;
        pq.add(new Tuple(0, 0, 0));

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while(!pq.isEmpty()){
            Tuple t = pq.peek();
            int dif = t.difference;
            int r = t.row;
            int c = t.col;
            pq.remove();

            if(r == n-1 && c == m-1) return dif;
            for(int i = 0; i < 4; i++){
                int nrow = r + dr[i];
                int ncol = c + dc[i];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m){
                    int newEffort = Math.max(dif, Math.abs(heights[r][c]-heights[nrow][ncol]));
                    if(newEffort < diff[nrow][ncol]){
                        diff[nrow][ncol] = newEffort;
                        pq.add(new Tuple(newEffort, nrow, ncol));
                    }
                }

            }

        }

        return 0;

    }
}