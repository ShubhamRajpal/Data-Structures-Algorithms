/*
  Problem Link: https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/
*/

//Approach (Just find the minRow, maxRow, minCol and maxCol where we see 1)
//T.C : O(n * m)
//S.C : O(1)
class Solution {
    public int minimumArea(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int startRow = 1000, startCol = 1000;
        int endRow = -1, endCol = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    startRow = Math.min(startRow, i);
                    startCol = Math.min(startCol, j);
                    endRow = Math.max(endRow, i);
                    endCol = Math.max(endCol, j);
                }
            }
        }

        return (endRow - startRow + 1) * (endCol - startCol + 1);
    }
}
