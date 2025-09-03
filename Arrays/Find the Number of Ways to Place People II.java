/*
    Problem Link: https://leetcode.com/problems/find-the-number-of-ways-to-place-people-ii
*/

//Approach (Using Sorting - Approach-2 of Find the Number of Ways to Place People I)
//T.C : O(n^2)
//S.C : O(1)
class Solution {
    public int numberOfPairs(int[][] points) {
        int n = points.length;
        int res = 0;
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]){
                return b[1] - a[1];
            }
                return a[0] - b[0];
        });

        for (int i = 0; i < n; i++) {
            int maxY = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {  
                if (points[j][1] <= points[i][1]){
                    if(points[j][1] > maxY) {
                        res++;
                    }
                    maxY = Math.max(maxY, points[j][1]);
                }
            }
        }

        return res;
    }
}
