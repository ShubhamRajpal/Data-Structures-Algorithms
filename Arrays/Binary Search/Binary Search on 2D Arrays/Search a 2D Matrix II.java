/*
  Problem Link: https://leetcode.com/problems/search-a-2d-matrix-ii/description/
*/

// Approach - Start from first row and last column(or last row and first column) and eliminate row or col 
// T.C: O(n + m)
// S.C: O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int i = 0, j = m - 1;
        while (i < n && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }

            if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }
}
