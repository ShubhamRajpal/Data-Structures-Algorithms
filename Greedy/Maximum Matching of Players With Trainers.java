/*
  Problem Link: https://leetcode.com/problems/maximum-matching-of-players-with-trainers/
*/

//Approach (Using sorting + greedily selecting)
//T.C : O(nlogn + mlogm + min(m, n)), where m = size of players and n = size of trainers
//S.C : O(1)
class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int count = 0;
        int i = 0, j = 0;
        int m = players.length, n = trainers.length;

        while (i < m && j < n) {
            if (players[i] <= trainers[j]) {
                count++;
                i++;
            }
            j++;
        }
        return count;
    }
}
