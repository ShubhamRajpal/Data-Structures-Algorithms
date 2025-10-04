/*
  Problem Link: https://leetcode.com/problems/container-with-most-water/
*/

//Why does my solution given below works ?
/*
Idea / Proof (Gathered from Discussion Section of Leetcode from some intelligent programmers) : 
    The widest container (using first and last line) is a good candidate, because of its width.
    Its water level is the height of the smaller one of first and last line. All other containers
    are less wide and thus would need a higher water level in order to hold more water. The smaller
    one of first and last line doesn't support a higher water level and can thus be safely removed from further consideration.
*/

//Approach (Using Greedy)
//T.C: O(n)
//S.C: O(1)
class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0, j = n-1;
        int maxi = Integer.MIN_VALUE;
        while(i < j){
            maxi = Math.max(maxi, (j-i) * Math.min(height[i], height[j]));
            if(height[i] <= height[j]){
                i++;
            }else if(height[i] > height[j]){
                j--;
            }
        }

        return maxi;
    }
}
