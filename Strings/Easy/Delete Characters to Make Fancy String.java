/*
  Problem Link: https://leetcode.com/problems/delete-characters-to-make-fancy-string/
*/

T.C: O(n)
S.C: O(n)
//Approach (Simply iterate and check for the condition)
class Solution {
    public String makeFancyString(String s) {
        int cnt = 1;
        char str[] = s.toCharArray();
        int n = str.length; 
        StringBuilder res = new StringBuilder ();
        res.append(str[0]);
        int i = 1;
        while(i < n){
            if(str[i] != str[i-1]) {
                cnt = 1;
            }
            else {
                cnt++;
            }

            if(cnt < 3){
                res.append(str[i]);
            }
            i++;
        }

        return res.toString();
    }
}
