/*
  Problem Link: https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/description/
*/

//Approach-1 (Simple iterating on digits)
//T.C : O(log10(n))
//S.C : O(log10(n))
class Solution {
    public int minMaxDifference(int num) {
        String minNum = Integer.toString(num);
        String maxNum = Integer.toString(num);
        int n = minNum.length();

        // Find the first digit in maxNum that is not '9' and replace all its occurrences with '9'
        char ch = ' ';
        for (int i = 0; i < n; i++) {
            if (maxNum.charAt(i) != '9') {
                ch = maxNum.charAt(i);
                break;
            }
        }

        if (ch != ' ') {
            maxNum = maxNum.replace(ch, '9');
        }

        // Replace all occurrences of the first digit in minNum with '0'
        ch = minNum.charAt(0);
        minNum = minNum.replace(ch, '0');

        return Integer.parseInt(maxNum) - Integer.parseInt(minNum);
    }
}


//Approach-2 (Using Java Standard Library functions)
//T.C : O(log10(n))
//S.C : O(log10(n))
class Solution {
    public int minMaxDifference(int num) {
        String sb1 = String.valueOf(num);
        String sb2 = String.valueOf(num);

        int n = sb1.length();
        char ch = '&', ch1 = '&';
        for (int i = 0; i < n; i++) {
            if (sb1.charAt(i) != '9') {
                ch = sb1.charAt(i);
                break;
            }
        }

        if (ch == '&') return num;

        sb1 = sb1.replace(ch, '9');

        ch1 = sb2.charAt(0);
        sb2 = sb2.replace(ch1, '0');

        return Integer.parseInt(sb1.toString()) - Integer.parseInt(sb2.toString());

    }
}
