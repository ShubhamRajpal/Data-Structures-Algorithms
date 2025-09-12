/*
  Problem Link: https://leetcode.com/problems/vowels-game-in-a-string/
*/

//Approach (Simple Math)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public boolean doesAliceWin(String s) {
        for (char ch : s.toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                return true;
            }
        }
        return false;
    }
}


//Approach-2 (Using one liner JCF)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public boolean doesAliceWin(String s) {
        return s.chars().anyMatch(ch -> "aeiou".indexOf(ch) >= 0);
    }
}
