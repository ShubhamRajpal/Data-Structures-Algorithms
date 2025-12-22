/*
  Problem Link: https://leetcode.com/problems/delete-columns-to-make-sorted-ii/
*/

//Approach (Iterate while keeping a check if it's safe to delete the column or not)
//T.C : O(rows * cols)
//S.C : O(rows)
class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;          // number of rows
        int cols = strs[0].length();     // number of columns

        int deletion = 0;
        boolean[] alreadySorted = new boolean[rows];

        for (int col = 0; col < cols; col++) {
            boolean deleted = false;

            // check if this column breaks lexicographical order
            for (int row = 0; row < rows - 1; row++) {
                if (!alreadySorted[row] &&
                    strs[row].charAt(col) > strs[row + 1].charAt(col)) {
                    deletion++;
                    deleted = true;
                    break;
                }
            }

            if (deleted) {
                continue;
            }

            // update alreadySorted status
            for (int i = 0; i < rows - 1; i++) {
                if (strs[i].charAt(col) < strs[i + 1].charAt(col)) {
                    alreadyDeleted[i] = true;
                }
            }
        }

        return deletion;
    }
}
