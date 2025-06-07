/*
  Problem Link: https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i
*/

//Approach-1 (trying at every index to find best substring)
//T.C : O(n^2)
//S.C : O(1)
class Solution {
    public String answerString(String word, int numFriends) {
        if(numFriends == 1) return word;
        int n = word.length();
        int maxLen = n - numFriends + 1;
        String ans = word.substring(0, maxLen);
        for(int i = 1; i < n; i++){
            String cur = i+maxLen <= n ? word.substring(i, i+maxLen) : word.substring(i);
            ans = ans.compareTo(cur) > 0 ? ans : cur;
        }

        return ans;
    }
}


//Approach-2 (2 Pointer)
//T.C : O(n)
//S.C : O(1)
class Solution {

    private int bestStartingPoint(String word, int n) {
        int i = 0, j = 1;
        while (j < n) {
            int k = 0;
            while (j + k < n && word.charAt(i + k) == word.charAt(j + k)) {
                k++;
            }
            if (j + k < n && word.charAt(i + k) < word.charAt(j + k)) {
                i = j;
                j = j + 1;
            } else {
                j = j + k + 1;
            }
        }

        return i;
    }

    public String answerString(String word, int numFriends) {
        int n = word.length();

        if (numFriends == 1) {
            return word;
        }

        int i = bestStartingPoint(word, n);
        int maxPossible = n - (numFriends - 1);
        int canTake = Math.min(maxPossible, n - i);

        return word.substring(i, i + canTake);
    }
}
