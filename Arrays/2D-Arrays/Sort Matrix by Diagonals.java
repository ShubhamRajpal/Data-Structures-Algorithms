/*
    Leetcode Link : https://leetcode.com/problems/sort-matrix-by-diagonals/
    
    Similar GfG Qn :  https://practice.geeksforgeeks.org/problems/diagonal-morning-assembly0028/1#
    Leetcode Qn Like this : Leetcode-498 (Diagonal Traverse) : https://leetcode.com/problems/diagonal-traverse/
    Leetcode Qn Like this : Leetcode-1329 (Sort the Matrix Diagonally) : https://leetcode.com/problems/sort-the-matrix-diagonally/
*/

/*
    Note : If you want to travel diaginally from t right side, you can use the key map as (i+j) instead of (i-j)
    Related Qn : https://leetcode.com/problems/diagonal-traverse/
*/

T.C: O(m*n * log(m+n))
S.C: O(m*n)
class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> diagMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int dis = i - j;
                diagMap.putIfAbsent(dis, new ArrayList<>());
                diagMap.get(dis).add(grid[i][j]);
            }
        }

        // Iterate throught each diagonal to sort them
        for (Map.Entry<Integer, List<Integer>> entry : diagMap.entrySet()) {
            List<Integer> temp = entry.getValue();
            if (entry.getKey() >= 0) {
                temp.sort(Collections.reverseOrder());
            } else {
                Collections.sort(temp);
            }
        }

        // Insert the sorted diagonals into grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int dis = i - j;
                grid[i][j] = diagMap.get(dis).remove(0);
            }
        }

        return grid;
    }
}
