/*
  Problem Link:  https://leetcode.com/problems/total-characters-in-string-after-transformations-i
*/

//Approach - Using frequency count in map
//T.C : O(n+t)
//S.C : O(26) ~= O(1)
class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int n = s.length();
        int mod = (int) (1e9 + 7);

        int[] mp = new int[26];
        for (char ch : s.toCharArray()) {
            mp[ch - 'a']++;
        }

        for (int cnt = 1; cnt <= t; cnt++) {
            int[] temp = new int[26];

            for (int i = 0; i < 26; i++) {
                char c = (char) (i + 'a');
                int charCnt = mp[i];

                if (c != 'z') {
                    char newChar = (char) (c + 1);
                    temp[newChar - 'a'] = (temp[newChar - 'a'] + charCnt) % mod;
                } else {
                    temp['a' - 'a'] = (temp['a' - 'a'] + charCnt) % mod;
                    temp['b' - 'a'] = (temp['b' - 'a'] + charCnt) % mod;
                }
            }

            mp = temp;
        }

        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            cnt = (cnt + mp[i]) % mod;
        }

        return cnt;

    }
}
