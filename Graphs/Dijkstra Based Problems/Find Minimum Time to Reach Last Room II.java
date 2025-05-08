/*
  Problem Link: https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/
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
        pq.add(new int[] { 0, 0, 0, 0 });
        // int curMoveTime = 1;
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        while (!pq.isEmpty()) {
            int curTime = pq.peek()[0];
            int curRow = pq.peek()[1];
            int curCol = pq.peek()[2];
            int curMoveTime = pq.peek()[3];
            pq.remove();

            if (curRow == n - 1 && curCol == m - 1)  return res[n - 1][m - 1];

            curMoveTime = (curMoveTime == 0 || curMoveTime == 2) ? 1 : 2;

            for (int i = 0; i < 4; i++) {
                int newRow = curRow + dx[i];
                int newCol = curCol + dy[i];
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m) {
                    int waitTime = Math.max(moveTime[newRow][newCol] - curTime, 0);
                    int finalTime = waitTime + curTime + curMoveTime;
                    if (finalTime < res[newRow][newCol]) {
                        res[newRow][newCol] = finalTime;
                        pq.offer(new int[] { finalTime, newRow, newCol, curMoveTime});
                    }
                }
            }

        }

        return -1;
    }
}
