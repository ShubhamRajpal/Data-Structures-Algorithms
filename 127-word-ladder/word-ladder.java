/*
    Time Complexity: O(N * wordLength * 26)
    Space Complexity: O(wordList.length)
    Problem Link: https://leetcode.com/problems/word-ladder/
*/


class Pair {
    String first;
    int second;

    public Pair(String f, int s) {
        first = f;
        second = s;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));
        Set<String> st = new HashSet<String>();
        for (int i = 0; i < wordList.size(); i++) {
            st.add(wordList.get(i));
        }

        if(!st.contains(endWord)) return 0;
        
        st.remove(beginWord);
        while (!q.isEmpty()) {
            Pair front = q.peek();
            String word = front.first;
            int steps = front.second;
            q.remove();
            if (word.equals(endWord)) return steps;
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replacedCharArray = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedString = new String(replacedCharArray);
                    if (st.contains(replacedString)) {
                        q.add(new Pair(replacedString, steps + 1));
                        st.remove(replacedString);
                    }
                }
            }

        }
        return 0;
    }
}