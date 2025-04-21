/*
  Problem Link: https://leetcode.com/problems/rabbits-in-forest/description/
  T.C- O(N)
  S.C- O(N)
*/

class Solution {
    public int numRabbits(int[] answers) {
        int n = answers.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            freq.put(answers[i], freq.getOrDefault(answers[i], 0) + 1);
        }

        int totalRabbits = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int groups = (int) Math.ceil((double) entry.getValue() / (entry.getKey() + 1));
            totalRabbits += (groups * (entry.getKey() + 1));
        }

        return totalRabbits;

    }
}
