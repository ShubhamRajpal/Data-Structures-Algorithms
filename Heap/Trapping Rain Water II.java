/*
  Problem Link: https://leetcode.com/problems/trapping-rain-water-ii/
*/

//Approach (used min-heap to get the min height boundary cell efficiently for each cell where water trapping is possible)
// T.C: O(m*n log(m*n))
// S.C: O(m*n)
  
class HeightMap {
    int height, row, col;

    public HeightMap(int height, int row, int col) {
        this.height = height;
        this.row = row;
        this.col = col;
    }
}

class Solution {
    public int trapRainWater(int[][] heightMap) {

        int n = heightMap.length;
        int m = heightMap[0].length;
        PriorityQueue<HeightMap> pq = new PriorityQueue<>((a, b) -> a.height - b.height);

        boolean[][] visited = new boolean[n][m];

        for (int row = 0; row < n; row++) {
            visited[row][0] = true;
            visited[row][m - 1] = true;
            pq.add(new HeightMap(heightMap[row][0], row, 0));
            pq.add(new HeightMap(heightMap[row][m - 1], row, m - 1));
        }

        for (int col = 0; col < m; col++) {
            visited[0][col] = true;
            visited[n - 1][col] = true;
            pq.add(new HeightMap(heightMap[0][col], 0, col));
            pq.add(new HeightMap(heightMap[n - 1][col], n - 1, col));
        }

        int totalTrappedWater = 0;
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        while (!pq.isEmpty()) {
            HeightMap top = pq.poll();
            int curHeight = top.height;
            int curRow = top.row;
            int curCol = top.col;

            for (int i = 0; i < 4; i++) {
                int newRow = curRow + dx[i];
                int newCol = curCol + dy[i];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && !visited[newRow][newCol]) {
                    int maxHeight = Math.max(0, curHeight - heightMap[newRow][newCol]);
                    totalTrappedWater += maxHeight;

                    pq.add(new HeightMap(Math.max(curHeight, heightMap[newRow][newCol]), newRow, newCol));
                    visited[newRow][newCol] = true;
                }
            }

        }
        return totalTrappedWater;
    }
}
