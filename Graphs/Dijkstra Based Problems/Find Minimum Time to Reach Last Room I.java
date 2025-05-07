/*
  Problem Link: https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/description/
*/

//Approach (Using Dijkstra's Algorithm)
//T.C : O(m*n log(m*n))
//S.C : O(m*n)
class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[][] res = new int[n][m];
        for (int[] it : res) {
            Arrays.fill(it, Integer.MAX_VALUE);
        }
        res[0][0] = 0;
        pq.add(new int[] { 0, 0, 0 });

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        while (!pq.isEmpty()) {
            int curTime = pq.peek()[0];
            int curRow = pq.peek()[1];
            int curCol = pq.peek()[2];
            pq.remove();

            if (curRow == n - 1 && curCol == m - 1)
                break;

            for (int i = 0; i < 4; i++) {
                int newRow = curRow + dx[i];
                int newCol = curCol + dy[i];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {
                    int reqTime = Math.max(moveTime[newRow][newCol] - curTime, 0);
                    int finalTime = reqTime + curTime + 1;
                    if (finalTime < res[newRow][newCol]) {
                        res[newRow][newCol] = finalTime;
                        pq.offer(new int[] { finalTime, newRow, newCol });
                    }
                }
            }

        }

        return res[n - 1][m - 1];

    }
}
