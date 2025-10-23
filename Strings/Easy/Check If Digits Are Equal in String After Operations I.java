/*
  Problem Link: https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i/
*/

//Approach (simple traverse and check)
//T.C : O(n^2)
//S.C : O(1)
class Solution {
    public boolean hasSameDigits(String s) {
        StringBuilder ans = new StringBuilder(s);
        int n = ans.length();
        while (n > 2) {

            for (int i = 0; i < n - 1; i++) {
                int ch1 = ans.charAt(i) - '0';
                int ch2 = ans.charAt(i + 1) - '0';
                int val = (ch1 + ch2) % 10;
                ans.setCharAt(i, (char) (val + '0'));
            }
            n--;
        }

        return ans.charAt(0) == ans.charAt(1);
    }
}
