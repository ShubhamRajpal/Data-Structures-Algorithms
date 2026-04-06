/*
  Problem Link: https://leetcode.com/problems/robot-return-to-origin/description/
*/

//Approach (simple simulation)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public boolean judgeCircle(String moves) {
        int left = 0, right = 0, up = 0, down = 0;

        for (int i = 0; i < moves.length(); i++) {
            left += moves.charAt(i) == 'L' ? 1 : 0;
            right += moves.charAt(i) == 'R' ? 1 : 0;
            up += moves.charAt(i) == 'U' ? 1 : 0;
            down += moves.charAt(i) == 'D' ? 1 : 0;
        }
        return left == right && up == down;
    }
}
