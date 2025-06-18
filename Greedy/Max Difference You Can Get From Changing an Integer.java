/*
  Problem Lik: https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/
*/

//Approach (replacing with 9 for maximum and carefully minimizing the number using greedy)
//T.C : O(log10(num))
//S.C : O(log10(num))
class Solution {
    public int maxDiff(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        String a = s, b = s;
        char ch1 = '&', ch2 = '&';
        for (char c : s.toCharArray()) {
            if (c - '0' < 9 && ch1 == '&') {
                ch1 = c;
            }

            if (c - '0' > 1 && ch2 == '&') {
                ch2 = c;
            }

            if (ch1 != '&' && ch2 != '&')
                break;
        }

        // Create min number with care to avoid leading zeros
        a = a.replace(ch1, '9');
        b = (ch2 == s.charAt(0)) ? b.replace(ch2, '1') : b.replace(ch2, '0');

        return Integer.parseInt(a) - Integer.parseInt(b);

    }
}
