/*
  Problem Link: https://leetcode.com/problems/walking-robot-simulation/
*/

//Simple Simulation
//T.C : O(m + n * maxValCommand), m = size of obstacles, n = size of commands
//S.C : O(m)
class Solution {

    public boolean checkObstacle(int x, int y, Set<String> obstacle) {
        if (obstacle.contains(x + "_" + y)) {
            return true;
        }

        return false;
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] directions = new int[4][2];

        directions[0] = new int[] { 0, 1 };
        directions[1] = new int[] { 1, 0 };
        directions[2] = new int[] { 0, -1 };
        directions[3] = new int[] { -1, 0 };

        int n = commands.length;
        int m = obstacles.length;

        Set<String> obstacle = new HashSet<>();
        for (int i = 0; i < m; i++) {
            String ob = obstacles[i][0] + "_" + obstacles[i][1];
            obstacle.add(ob);
        }

        int dir = 0;
        int prevX = 0, prevY = 0;
        long maxDist = -1L;
        for (int i = 0; i < n; i++) {
            int com = commands[i];
            if (com == -1) { //  rotate right 90 deg
                dir = (dir + 1) % 4;
            } else if (com == -2) { // rotate left 90 deg
                dir = ((dir - 1) + 4) % 4;
            } else {
                int curX = prevX + directions[dir][0];
                int curY = prevY + directions[dir][1];

                while (!checkObstacle(curX, curY, obstacle) && com > 0) {
                    long dist = (1L * curX * curX) + (1L * curY * curY);
                    maxDist = Math.max(maxDist, dist);
                    com--;
                    curX += directions[dir][0];
                    curY += directions[dir][1];

                }

                prevX = curX - directions[dir][0];
                prevY = curY - directions[dir][1];
            }
        }

        int ans = (int) maxDist;
        return ans == -1L ? 0 : ans;
    }
}
