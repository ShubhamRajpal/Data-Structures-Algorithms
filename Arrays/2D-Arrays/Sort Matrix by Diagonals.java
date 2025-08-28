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


Approach-2 (Simple Simulation)
T.C: O(n*m*log(n*m))    
S.C: O(n*m)    
class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; i+j < n; j++) {
                temp.add(grid[i + j][j]);
            }

            temp.sort(Collections.reverseOrder());
            System.out.println(temp);
            for (int j = 0; j < temp.size(); j++) {
                grid[i + j][j] = temp.get(j);
            }
        }

        for (int j = 1; j < m; j++) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i+j < m; i++) {
                temp.add(grid[i][i + j]);
            }

            Collections.sort(temp);
            for (int i = 0; i < temp.size(); i++) {
                grid[i][i + j] = temp.get(i);
            }
        }

        return grid;
    }
}
