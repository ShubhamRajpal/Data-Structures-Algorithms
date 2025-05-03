/*
  Problem Link: https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/description/
*/
//Approach (Greedy)
//T.C : O(n)
//S.C : O(1)
class Solution {

    private int findOperations(int e, int[] tops, int[] bottoms) {

        int swaptops = 0, swapBottoms = 0;
        for (int i = 0; i < tops.length; i++) {
            if (tops[i] != e && bottoms[i] != e)
                return -1;
            else if (tops[i] != e) {
                swaptops++;
            } else if (bottoms[i] != e) {
                swapBottoms++;
            }

        }

        return Math.min(swaptops, swapBottoms);
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int maxTops = findOperations(tops[0], tops, bottoms);
        int maxBottoms = findOperations(bottoms[0], tops, bottoms);

        return (maxTops == -1 && maxBottoms == -1) ? -1 : Math.max(maxTops, maxBottoms);
    }
}
