/*
  Problem Link: https://leetcode.com/problems/matrix-similarity-after-cyclic-shifts/description/
*/

//Approach-1 (Simulation + extra space + your own rotation logic)
//T.C : O(m*n)
//S.C : O(m*n)
class Solution {

    public void reverse(int st, int end, int[] arr){
        
        while(st <= end){
            int temp = arr[st];
            arr[st]  = arr[end];
            arr[end] = temp;
            st++;
            end--;
        }
    }

    public void rotate(int[] arr, int dir, int k) {
        int n = arr.length;

        reverse(0, k - 1, arr);
        reverse(k, n - 1, arr);
        reverse(0, n - 1, arr);
    }

    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        
        k %= n;
        int[][] temp = new int[m][n];
        for (int row = 0; row < m; row++) {
            temp[row] = mat[row].clone();
            if (row % 2 == 0)
                rotate(temp[row], 1, k);
            else
                rotate(temp[row], -1, k);
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(temp[i][j] != mat[i][j]){
                    return false;
                }
            }
        }

        return true;
    }
}

//Approach-2 (Without rotation and O(1) space)
//T.C : O(m*n)
//S.C : O(1)
class Solution {

    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        k %= n;

        if (k == 0) {
            return true;
        }

        int j = k;
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < m; row++) {
                if (mat[row][col] != mat[row][j % n]) {
                    return false;
                }
            }

            j++;
        }

        return true;
    }
}
