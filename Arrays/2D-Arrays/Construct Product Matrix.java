/*
  Problem Link: https://leetcode.com/problems/construct-product-matrix/description/
  Similar Concept Qn: LC 238: Product of array except self(https://leetcode.com/problems/product-of-array-except-self/description/)
*/

//Approach - Suffix and Prefix Products
//T.C : O(n*m)
//S.C : O(n*m) , for the result to be returned
class Solution {

    long M = 12345;

    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] p = new int[n][m];

        long prod = 1L;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                p[i][j] = (int) prod;
                prod = (prod * grid[i][j]) % M;
            }
        }

        prod = 1L;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                p[i][j] = (int) ((p[i][j] * prod) % M);
                prod = (prod * grid[i][j]) % M;
            }
        }

        return p;

    }
}
