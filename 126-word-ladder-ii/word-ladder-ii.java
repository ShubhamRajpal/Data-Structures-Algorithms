
class Solution {
    String s;
    HashMap<String, Integer> mp; // Map to store stpes required to a particular string
    List<List<String>> ans;

    public void dfs(List<String> seq, String Word) {
        if (Word.equals(s)) {

            List<String> dup = new ArrayList<>(seq);
            Collections.reverse(dup);
            ans.add(dup);
            return;
        }

        int steps = mp.get(Word);
        int len = Word.length();

        for (int i = 0; i < len; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                char[] replacedCharArray = Word.toCharArray();
                replacedCharArray[i] = ch;
                String replacedWord = new String(replacedCharArray);
                // If replaced String is present in map, add it to sequence
                // We avoid extra conversions using this check
                if (mp.containsKey(replacedWord) && mp.get(replacedWord) + 1 == steps) {
                    seq.add(replacedWord);
                    dfs(seq, replacedWord);
                    seq.remove(seq.size() - 1);
                }

            }
        }

    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> st = new HashSet<>();
        for (int i = 0; i < wordList.size(); i++) {
            st.add(wordList.get(i));
        }

        // Store no. of steps for each transformation
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        s = beginWord;
        mp = new HashMap<>();
        mp.put(beginWord, 1);
        int len = beginWord.length();
        st.remove(beginWord);
        while (!q.isEmpty()) {
            String word = q.peek();
            int steps = mp.get(word);
            q.remove();

            if (word.equals(endWord))
                break;

            for (int i = 0; i < len; i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] replacedCharArray = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);
                    if (st.contains(replacedWord)) {
                        q.add(replacedWord);
                        st.remove(replacedWord);
                        mp.put(replacedWord, steps + 1);
                    }

                }
            }
        }

        // Bactrack from endWord to find Sequences
        // This approach works because we know shortest length of sequence so we won't
        // explore unecessary sequences of different lengths tha would have occured if
        // we had started from startWord. Tha's why we start from end word because we
        // already know the shortest length to reach endWord and there will be lesser
        // no. of paths to explore.
        ans = new ArrayList<>();
        if (mp.containsKey(endWord)) {
            List<String> seq = new ArrayList<>();
            seq.add(endWord);
            dfs(seq, endWord);
        }

        return ans;
    }
}