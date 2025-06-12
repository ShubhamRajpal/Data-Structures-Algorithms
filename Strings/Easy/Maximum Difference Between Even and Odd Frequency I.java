/*
  Problem Link: https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/description/
*/

//Approach (Straught forward maxOdd - minEven)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int maxDifference(String s) {
        int[] freq = new int[26];
        int maxOdd = Integer.MIN_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                if (freq[i] % 2 == 0 && freq[i] < minEven) {
                    minEven = freq[i];
                } else if (freq[i] % 2 != 0 && freq[i] > maxOdd) {
                    maxOdd = freq[i];
                }
            }

        }

        return maxOdd - minEven;
    }
}
