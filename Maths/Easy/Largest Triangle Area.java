/*
  Problem Link: https://leetcode.com/problems/largest-triangle-area/
*/

//Approach (Explore all triangles - Heron's Formula or Shoelace Formula for finding area)
//T.C : O(n^3)
//S.C : O(1)
class Solution {

    private double calcArea(int[] p1, int[] p2, int[] p3){
        double x1 = p1[0], y1 = p1[1];
        double x2 = p2[0], y2 = p2[1];
        double x3 = p3[0], y3 = p3[1];

        // Heron's formula
        double a = Math.hypot(x1-x2, y1-y2);
        double b = Math.hypot(x1-x3, y1-y3);
        double c = Math.hypot(x2-x3, y2-y3);
        double s = (a+b+c) * 0.5;
        double heron = Math.sqrt((s * (s-a) * (s-b) * (s-c))); 
        
        // Shoelace formula
        double shoelace = 0.5 * Math.abs(
            x1 * (y2 - y3) +
            x2 * (y3 - y1) +
            x3 * (y1 - y2)
        );

        return shoelace;

    }


    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double maxArea = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    double curArea = calcArea(points[i], points[j], points[k]);
                    if(maxArea < curArea){
                        maxArea = curArea;
                    }
                }
            }
        }

        return maxArea;
    }
}
