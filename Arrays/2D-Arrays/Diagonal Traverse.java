
/*
    Note : If you want to travel diagonally from right side, you can use the key map as (i-j) instead of (i+j)
    Related Qn : https://leetcode.com/problems/sort-the-matrix-diagonally/
*/

/*
    Problem Link: https://leetcode.com/problems/diagonal-traverse/
*/

//Approach-1 (Add all diagonal elements for each value(=row+column) into a map and sort the alternate enteries in map)
//T.C : O(m*n * log(m+n)), log because we are using ordered_map
//S.C : O(m*n)
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        
        // Use HashMap to store diagonals
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Fill the map with diagonals
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key = i + j;
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(mat[i][j]);
            }
        }
        
        List<Integer> resultList = new ArrayList<>();
        boolean flip = true;
        
        // Process diagonals in increasing order of keys
        for (int k = 0; k <= m + n - 2; k++) {
            List<Integer> diagonal = map.get(k);
            if (diagonal == null) continue;
            
            if (flip) {
                Collections.reverse(diagonal);
            }
            resultList.addAll(diagonal);
            
            flip = !flip;
        }
        
        // Convert result list to array
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        
        return result;
    }
}



//Approach-2 (Simple Simulation)
//T.C : O(m*n)
//S.C : O(1)
class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] ans = new int[n * m];
        ans[0] = mat[0][0];
        int k = 1;
        int row = 0, col = 0;
        int r = n, c = m;
        int dir = 1;
        while (row < r && col < c) {
            if (col == m - 1) {
                row++;
            } else if (row == n - 1) {
                col++;
            } else if (row == 0 && col < m) {
                col++;
            } else if (col == 0 && row < n) {
                row++;
            }

            if (dir == 0) {
                while (row >= 0 && col < m) {
                    ans[k++] = mat[row][col];
                    row--;
                    col++;
                }
                row++;
                col--;
            } else {
                while (row < n && col >= 0) {
                    ans[k++] = mat[row][col];
                    row++;
                    col--;
                }
                row--;
                col++;
            }

            dir = 1 - dir;

            if (row == n - 1 && col == m - 1)
                break;

        }

        return ans;

    }
}
