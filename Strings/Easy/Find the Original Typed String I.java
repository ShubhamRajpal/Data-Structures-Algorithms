/*
  Problem Link: https://leetcode.com/problems/find-the-original-typed-string-i/
*/

Approach (checking adjacent chars)
T.C.: O(n)
S.C.: O(1)
class Solution {
    public int possibleStringCount(String word) {
        int n = word.length();
        int cnt = 1;
        for(int i = 1;i < n;i++){
            if(word.charAt(i) == word.charAt(i-1)){
                cnt++;
            }
        }

        return cnt;
    }
}
