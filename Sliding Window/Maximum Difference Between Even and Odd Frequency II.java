/*
  Problem Link: https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-ii
*/

//Approach  (Sliding Window)
//T.C : O(20 * n) ~= O(n)
//S.C : O(1)
class Solution {

    private int getState(int a, int b) {
        int parity_a = a % 2; //odd : parity will be 1 , even : parity will be 0
        int parity_b = b % 2;

        if (parity_a == 0 && parity_b == 0) return 0; // even even(00)
        if (parity_a == 0 && parity_b == 1) return 1; // even odd(01)
        if (parity_a == 1 && parity_b == 0) return 2; // odd even(10)

        return 3; // odd odd(11)
    }

    public int maxDifference(String s, int k) {
        int n = s.length();
        char[] str = s.toCharArray();

        int result = Integer.MIN_VALUE;

        //Step-1 Try all possible pairs a and b from ('0' to '4') where a != b
        for (char a = '0'; a <= '4'; a++) {
            for (char b = '0'; b <= '4'; b++) {

                if (a == b) continue;

                //stateMinPrev_a_b[state] = stores the smallest value of count of a - count of b
                int[] prevStateMin_a_b = new int[4]; 
                Arrays.fill(prevStateMin_a_b, Integer.MAX_VALUE);

                // count of a nd b till index r
                int count_a = 0;
                int count_b = 0;

                //count of prev_a and prev_b till index l
                int prev_a = 0;
                int prev_b = 0;

                int l = -1, r = 0;
                while (r < n) {

                    count_a += str[r] == a ? 1 : 0;
                    count_b += str[r] == b ? 1 : 0;

                    // Now Try to shrink from index l
                    while (r - l >= k && count_a - prev_a >= 1 && count_b - prev_b >= 2) {

                        int leftState = getState(prev_a, prev_b);
                        prevStateMin_a_b[leftState] = Math.min(prevStateMin_a_b[leftState], prev_a - prev_b);
                        l++;

                        prev_a += str[l] == a ? 1 : 0;
                        prev_b += str[l] == b ? 1 : 0;
                    }

                    int rightState = getState(count_a, count_b);
                    int bestLeftState = rightState ^ 2;
                    int bestLeftMinValue = prevStateMin_a_b[bestLeftState];

                    if (bestLeftMinValue != Integer.MAX_VALUE) {
                        result = Math.max(result, (count_a - count_b) - bestLeftMinValue);
                    }
                    r++;
                }
            }
        }
        return result;
    }
}
