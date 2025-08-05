/*
  Problem Link: https://leetcode.com/problems/fruits-into-baskets-ii/
*/

//Approach (Simply do what is said)
// T.C: O(N^2)
// S.C: O(1)
class Solution {
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            boolean fl = false;
            for (int j = 0; j < n; j++) {
                if (baskets[j] >= fruits[i]) {
                    fl = true;
                    baskets[j] = 0;
                    break;
                }
            }

            if (!fl) cnt++;
        }

        return cnt;
    }
}
