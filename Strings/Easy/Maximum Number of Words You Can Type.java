/*
  Problem Link: https://leetcode.com/problems/maximum-number-of-words-you-can-type/
*/

//Approach - simple iteration
//T.C : O(m+n), m = length of text, n = length of brokenLetters
//S.C : O(1)
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        int cnt = 0;
        boolean canType = true;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == ' ') {
                if (canType) {
                    cnt++;
                }
                canType = true;
            } else {
                if (brokenLetters.indexOf(ch) != -1) {
                    canType = false;
                }
            }
        }

        if (canType)
            cnt++;

        return cnt;
    }
}
