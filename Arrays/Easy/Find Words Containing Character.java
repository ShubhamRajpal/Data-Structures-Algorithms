/*
  Problem Link: https://leetcode.com/problems/find-words-containing-character
*/

//T.C : O(m*n) 
//S.C : O(1)
class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (words[i].chars().anyMatch(ch -> ch == x)) {
                result.add(i);
            }
        }

        return result;
    }
}
