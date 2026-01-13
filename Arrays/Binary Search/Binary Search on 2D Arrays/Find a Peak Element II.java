/*
  Problem Link: https://leetcode.com/problems/find-a-peak-element-ii/description/
*/

// T.C: O(n log m)
// S.C: O(1)
class Solution {

    public int maxElementInCol(int n, int col, int[][] mat) {
        int maxElement = -1;
        int maxRowInd = -1;
        for (int i = 0; i < n; i++) {
            if (maxElement < mat[i][col]) {
                maxElement = mat[i][col];
                maxRowInd = i;
            }
        }

        return maxRowInd;
    }

    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int low = 0, high = m - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int maxRowInd = maxElementInCol(n, mid, mat);

            int left = (mid - 1 >= 0) ? mat[maxRowInd][mid - 1] : -1;
            int right = (mid + 1 < m) ? mat[maxRowInd][mid + 1] : -1;

            if (mat[maxRowInd][mid] > left && mat[maxRowInd][mid] > right) {
                return new int[] { maxRowInd, mid };
            } else if (mat[maxRowInd][mid] < left) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[] { -1, -1 };
    }
}
