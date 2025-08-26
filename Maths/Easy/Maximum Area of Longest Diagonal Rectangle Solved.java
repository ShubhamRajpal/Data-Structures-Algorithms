/*
  Problem Link: https://leetcode.com/problems/maximum-area-of-longest-diagonal-rectangle/
*/

//Approach (Simple and straight forward)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int n = dimensions.length;
        int maxArea = -1;
        double longestDiagonal = 0;
        for (int it[] : dimensions) {
            double l = it[0];
            double w = it[1];
            double diagonal = Math.sqrt(l * l + w * w);

            if (longestDiagonal < diagonal) {
                longestDiagonal = diagonal;
                maxArea = (int) (l * w);
            } else if (longestDiagonal == diagonal) {
                maxArea = Math.max(maxArea, (int) (l * w));
            }
        }

        return maxArea;
    }
}
