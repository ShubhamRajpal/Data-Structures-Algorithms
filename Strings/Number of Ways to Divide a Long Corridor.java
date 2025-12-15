/*
  Problem Link: https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/
  Comments: Try DP approach and add code
*/

//Approach-1 (Using simple iteration)
//T.C : O(n)
//S.C : O(n)
public class Solution {
    int mod = 1000000007;

    public int numberOfWays(String corridor) {
        ArrayList<Integer> posSeats = new ArrayList<>();

        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                posSeats.add(i);
            }
        }

        if (posSeats.size() % 2 != 0 || posSeats.size() == 0) {
            return 0;
        }

        long result = 1;
        int prev = posSeats.get(1); // End index of the starting subarray having 2 seats

        for (int i = 2; i < posSeats.size(); i += 2) {
            int length = posSeats.get(i) - prev;
            result = (result * length) % mod;

            prev = posSeats.get(i + 1);
        }

        return (int) result;
    }
}

//Approach-2 (Without extra space)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int numberOfWays(String corridor) {
        int n = corridor.length();
        long ways = 1;
        int mod = (int) 1e9 + 7;
        int prevPairLast = -1;
        int cntSeats = 0, totalSeats = 0;
        
        for (int i = 0; i < n; i++) {
            if (corridor.charAt(i) == 'S') {
                cntSeats++;
                totalSeats++;

                if (cntSeats == 2) {
                    prevPairLast = i;
                    cntSeats = 0;
                } else if (cntSeats == 1 && prevPairLast != -1) {
                    ways = (ways * (i - prevPairLast)) % mod;
                }
            }

        }

        if (totalSeats % 2 != 0 || totalSeats == 0) {
            return 0;
        }

        return (int) ways;
    }
}
