/*
  Problem Link: https://leetcode.com/problems/search-a-2d-matrix/
*/
 
// Approach-1 (Brute)
// T.C: O(n*m)
// S.C: O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        for(int i = 0;i < m; i++){
            for(int j = 0; j < m; j++){
                if(target == matrix[i][j])
                    return true;
            }
        }
        return false;
    }
}

// Approach-2 (Better) For every row, use binary search to find target
// T.C: O(n log m)
// S.C: O(1)
class Solution {
    public boolean check(int[] arr, int target) {
        
        int lo = 0, hi = arr.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] == target) {
                return true;
            }

            if (arr[mid] < target) {
                lo = mid + 1;
            }

            else {
                hi = mid - 1;
            }

        }

        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            if (check(matrix[i], target)) {
                return true;
            }
        }
        return false;
    }
}

// Approach-3 (Optimal) Flatten the 2d array into 1D array
// T.C: O(log(m*n))
// S.C: O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int n = matrix.length;
        int m = matrix[0].length;

        int low = 0, high = (n * m) - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int row = mid / m;
            int col = mid % m;
            if (matrix[row][col] == target) {
                return true;
            }

            if (matrix[row][col] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;

    }
}
