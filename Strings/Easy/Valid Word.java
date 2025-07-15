/*
  Problem Link: https://leetcode.com/problems/valid-word/
*/

Approach (Simple Traverse and Check)
T.C.: O(n)
S.C.: O(1)
class Solution {
    public boolean isValid(String word) {

        if (word.length() < 3) return false;

        int vowelCount = 0, consonantCount = 0;
        for (char ch : word.toCharArray()) {
            if (Character.isLetter(ch)) {
                char temp = Character.toLowerCase(ch);
                if (temp == 'a' || temp == 'e' || temp == 'i' || temp == 'o' || temp == 'u') {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            } else if (!Character.isDigit(ch)) {
                return false;
            }
        }

        return vowelCount >= 1 && consonantCount >= 1;
    }
}
