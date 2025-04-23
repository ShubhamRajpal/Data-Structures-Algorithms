/*
  Problem Link: https://leetcode.com/problems/count-largest-group/description/
*/

//Approach(Simulation)
//T.C- O(N * d) d = Average no. of digits in all numbers
//S.C- O(N)
class Solution {
    private int getdigitSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int countLargestGroup(int n) {
        Map<Integer, Integer> groups = new HashMap<>();
        int maxSize = 0, cnt = 0;
        for (int i = 1; i <= n; i++) {
            int sum = getdigitSum(i);
            groups.put(sum, groups.getOrDefault(sum, 0) + 1);
            maxSize = Math.max(maxSize, groups.get(sum));
        }

        for (Map.Entry<Integer, Integer> entry : groups.entrySet()) {
            if (entry.getValue() == maxSize) {
                cnt++;
            }
        }

        return cnt;
    }
}
