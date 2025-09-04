/*
  Problem Link: https://leetcode.com/problems/find-closest-person/description/
*/

Approach (Compare the distances from Person1 and Person2 to Person3)
T.C: O(1)
S.C: O(1)
class Solution {
    public int findClosest(int x, int y, int z) {
        int distP1 = Math.abs(x-z);
        int distP2 = Math.abs(y-z);

        if(distP1 == distP2)  return 0;
        
        return distP1 > distP2 ? 2 : 1;
    }
}
