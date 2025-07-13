/*
  Problem Link: https://leetcode.com/problems/the-earliest-and-latest-rounds-where-players-compete/description/
*/

//Approach : (Recursion)
//T.C : T(n) = n^2 * T(n/2) -> Homework for you. Comment in the video your solution
//S.C : No extra space taken (ignoring the system recursion space)
class Solution {
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {

        int P1 = firstPlayer;
        int P2 = secondPlayer;

        if (P1 + P2 == n + 1) {
            return new int[] { 1, 1 };
        }

        if (P1 > n - P2 + 1) {
            int temp = n - P1 + 1;
            P1 = n - P2 + 1;
            P2 = temp;
        }

        int earliestRound = Integer.MAX_VALUE;
        int latestRound = 0;
        int nextRoundRemainingPlayers = (n + 1) / 2;
        
        // Case 1:  When both P1 and P2 are in left Half
        if (P2 * 2 < n + 1) {

            int leftCount = P1 - 1;
            int midCount = P2 - P1 - 1;
            for (int survivorsLeft = 0; survivorsLeft <= leftCount; survivorsLeft++) {
                for (int survivorsMid = 0; survivorsMid <= midCount; survivorsMid++) {
                    int nextPosP1 = survivorsLeft + 1;
                    int nextPosP2 = nextPosP1 + survivorsMid + 1;

                    int[] res = earliestAndLatest(nextRoundRemainingPlayers, nextPosP1, nextPosP2);
                    earliestRound = Math.min(earliestRound, 1 + res[0]);
                    latestRound = Math.max(latestRound, 1 + res[1]);
                }
            }
            // Case 1:  When both P1 and P2 are in opposite Half
        } else {
            int fightsP2 = n - P2 + 1;
            int leftCount = P1 - 1;
            int midCount = fightsP2 - P1 - 1;
            int remainingMidSurvivors = P2 - fightsP2 - 1;

            for (int survivorsLeft = 0; survivorsLeft <= leftCount; survivorsLeft++) {
                for (int survivorsMid = 0; survivorsMid <= midCount; survivorsMid++) {
                    int nextPosP1 = survivorsLeft + 1;
                    int nextPosP2 = nextPosP1 + survivorsMid + (remainingMidSurvivors+1)/2 + 1;

                    int[] res = earliestAndLatest(nextRoundRemainingPlayers, nextPosP1, nextPosP2);
                    earliestRound = Math.min(earliestRound, 1 + res[0]);
                    latestRound = Math.max(latestRound, 1 + res[1]);
                }
            }
        }

        return new int[]{earliestRound, latestRound};

    }
}
