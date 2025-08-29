/*
  Problem Link: https://leetcode.com/problems/alice-and-bob-playing-flower-game/  
*/

//Approach - Using simple Maths(Alice will win if and only if x+y is odd)
//T.C : O(1)
//S.C : O(1)
class Solution {
    public long flowerGame(int n, int m) {
        long odd1 = (n+1)/2, even1 = n/2;
        long odd2 = (m+1)/2, even2 = m/2;

        long cnt1 = odd1 * even2;
        long cnt2 = odd2 * even1;

        return cnt1+cnt2;
    }
}
