/*
  Problem Link: https://leetcode.com/problems/find-the-string-with-lcp/ 
*/

//Approach (Using Greedy Allocation + Comparing LCP)
//T.C : O(n^2)
//S.C : O(n^2)
class Solution {

    private int[][] checkLCP(String word) {
        int n = word.length();
        int[][] lcp = new int[n][n];

        // last column
        for (int i = 0; i < n; i++) {
            lcp[i][n - 1] = (word.charAt(i) == word.charAt(n - 1)) ? 1 : 0;
        }

        // last row
        for (int j = 0; j < n; j++) {
            lcp[n - 1][j] = (word.charAt(n - 1) == word.charAt(j)) ? 1 : 0;
        }

        // fill rest bottom-up
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (word.charAt(i) == word.charAt(j)) {
                    lcp[i][j] = 1 + lcp[i + 1][j + 1];
                } else {
                    lcp[i][j] = 0;
                }
            }
        }

        return lcp;
    }

    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];

        // initialize with placeholder
        for (int i = 0; i < n; i++) {
            word[i] = '$';
        }

        for (int i = 0; i < n; i++) {

            // Step 1: try to match with previous
            for (int j = 0; j < i; j++) {
                if (lcp[j][i] != 0) {
                    word[i] = word[j];
                    break;
                }
            }

            // Step 2: assign smallest valid character
            if (word[i] == '$') {
                boolean[] forbidden = new boolean[26];

                for (int j = 0; j < i; j++) {
                    if (lcp[j][i] == 0) {
                        forbidden[word[j] - 'a'] = true;
                    }
                }

                for (int c = 0; c < 26; c++) {
                    if (!forbidden[c]) {
                        word[i] = (char) ('a' + c);
                        break;
                    }
                }

                // no valid character possible
                if (word[i] == '$') {
                    return "";
                }
            }
        }

        String result = new String(word);

        // Final validation
        int[][] computed = checkLCP(result);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computed[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }

        return result;
    }
}



//Approach-2 (Using Greedy Allocation + Without finding LCP)
//T.C : O(n^2)
//S.C : O(n) for word
class Solution {

    public boolean checkLCP(String word, int[][] lcp) {
        int n = word.length();

        // Last column
        for (int i = 0; i < n; i++) {
            if (word.charAt(i) != word.charAt(n - 1)) {
                if (lcp[i][n - 1] != 0) return false;
            } else {
                if (lcp[i][n - 1] != 1) return false;
            }
        }

        // Last row
        for (int j = 0; j < n; j++) {
            if (word.charAt(n - 1) != word.charAt(j)) {
                if (lcp[n - 1][j] != 0) return false;
            } else {
                if (lcp[n - 1][j] != 1) return false;
            }
        }

        // Remaining cells
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (word.charAt(i) == word.charAt(j)) {
                    if (lcp[i][j] != 1 + lcp[i + 1][j + 1]) return false;
                } else {
                    if (lcp[i][j] != 0) return false;
                }
            }
        }

        return true;
    }

    public String findTheString(int[][] lcp) {
        int n = lcp.length;

        char[] word = new char[n];
        for (int i = 0; i < n; i++) {
            word[i] = '$';
        }

        for (int i = 0; i < n; i++) {

            // Try to match with previous
            for (int j = 0; j < i; j++) {
                if (lcp[j][i] != 0) {
                    word[i] = word[j];
                    break;
                }
            }

            // Assign smallest valid character
            if (word[i] == '$') {
                boolean[] forbidden = new boolean[26];

                for (int j = 0; j < i; j++) {
                    if (lcp[j][i] == 0) {
                        forbidden[word[j] - 'a'] = true;
                    }
                }

                for (int idx = 0; idx < 26; idx++) {
                    if (!forbidden[idx]) {
                        word[i] = (char) ('a' + idx);
                        break;
                    }
                }

                if (word[i] == '$') {
                    return "";
                }
            }
        }

        String result = new String(word);
        return checkLCP(result, lcp) ? result : "";
    }
}
