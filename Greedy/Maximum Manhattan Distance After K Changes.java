/*
  Problem Link: https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes 
*/

//Approach (Iterating and finding best at each point of time)
//T.C : O(n)
//S.C : O(n)
class Solution {
    public int maxDistance(String s, int k) {
        int n = s.length();
        int maxDist = 0;
        int north = 0, south = 0, east = 0, west = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'N')
                north++;
            else if (ch == 'S')
                south++;
            else if (ch == 'E')
                east++;
            else
                west++;

            int netY = Math.abs(north - south);
            int netX = Math.abs(east - west);
            int initialDis = netY + netX;

            // Modifying Less frequent chars in either horizontal or vertical direction would increase the manhattan distance by 2
            int harmfulMoves = Math.min(north, south) + Math.min(east, west);
            int possibleIncrease = Math.min(k, harmfulMoves) * 2;

            int maxPossible = initialDis + possibleIncrease;
            maxDist = Math.max(maxDist, maxPossible);
        }

        return maxDist;

    }
}
