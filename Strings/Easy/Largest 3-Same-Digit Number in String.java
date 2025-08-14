/*
  Problem Link:  https://leetcode.com/problems/largest-3-same-digit-number-in-string/
*/

// Approach (Iterate and generate string for largest number)
T.C: O(n)
S.C: O(1)
class Solution {
    public String largestGoodInteger(String num) {
        int max = -1;

        int n = num.length();
        for (int i = 1; i < n - 1; i++) {
            int cur = Integer.parseInt(num.substring(i - 1, i + 2));
            if (num.charAt(i) - '0' == num.charAt(i - 1) - '0' && num.charAt(i) - '0' == num.charAt(i + 1) - '0') {
                if (cur > max) {
                    max = cur;
                }
            }
        }

        if (max == -1)
            return "";

        return max == 0 ? "000" : String.valueOf(max);
    }
}
