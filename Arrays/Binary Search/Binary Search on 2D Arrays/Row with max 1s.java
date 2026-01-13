/*
  Problem Link: https://www.geeksforgeeks.org/problems/row-with-max-1s0023/1  
*/

// Approach (For every row, find first column where 1 appears using lower bound and calculate count of 1s) 
// T.C: O(m log n)
// S.C: O(1)
class Solution {
    public int lowerBound(int[] row) {

        int n = row.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (row[mid] == 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }
    
    public int rowWithMax1s(int mat[][]) {
        
        int m = mat.length;
        int n = mat[0].length;
        int maxOnes = -1;
        int smallestOnesRow = -1;

        for (int row = 0; row < m; row++) {

            int rowSum = n - lowerBound(mat[row]);
            if (rowSum > maxOnes) {
                maxOnes = rowSum;
                smallestOnesRow = row;
            }
        }

        return smallestOnesRow;
        
    }
}
