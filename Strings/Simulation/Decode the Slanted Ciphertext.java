/*
  Problem Link: https://leetcode.com/problems/decode-the-slanted-ciphertext/
*/

// Approach-1 (Simple simulation with extra space)
// T.C : O(l), l = encodedText.length()
// S.C : O(l), all characters of encodedText in matrix
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        int cols = n / rows;

        char[][] mat = new char[rows][cols];
        for (int i = 0; i < n; i++) {
            mat[i / cols][i % cols] = encodedText.charAt(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < cols; j++) {
            for (int row = 0, col = j; row < rows && col < cols; row++, col++) {
                sb.append(mat[row][col]);
            }
        }

        String res = sb.toString();

        return res.replaceAll("\\s+$", "");

    }
}

//Approach-2 (Without extra space)
//T.C : O(l), l = encodedText.length()
//S.C : O(1)
class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int l = encodedText.length();
        int columns = l / rows;

        StringBuilder originalText = new StringBuilder();

        for (int col = 0; col < columns; col++) {
            for (int j = col; j < l; j += (columns + 1)) {
                originalText.append(encodedText.charAt(j));
            }
        }

        // Remove trailing spaces
        while (originalText.length() > 0 && 
               originalText.charAt(originalText.length() - 1) == ' ') {
            originalText.deleteCharAt(originalText.length() - 1);
        }

        return originalText.toString();
    }
}
