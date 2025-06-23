/*
  Problem Link: https://leetcode.com/problems/sum-of-k-mirror-numbers/
*/

//Approach - Using palindrome generation logic
//T.C : ~O((10 ^ D) * D), where D = number of digits in k-mirror number
//S.C : O(D), where D = number of digits in k-mirror number
class Solution {
    
    // Check if a string is a palindrome
    private boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left <= right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    // Convert a number to base-k string representation
    private String convertToBaseK(long num, int k) {
        if (num == 0) return "0";
        
        StringBuilder res = new StringBuilder();
        while (num > 0) {
            res.append(num % k);
            num /= k;
        }
        return res.toString();
    }

    public long kMirror(int k, int n) {
        long sum = 0;
        int length = 1;

        while (n > 0) {
            int halfLength = (length + 1) / 2;
            long minNum = (long)Math.pow(10, halfLength - 1);
            long maxNum = (long)Math.pow(10, halfLength) - 1;

            for (long num = minNum; num <= maxNum; num++) {
                String firstHalf = Long.toString(num);
                String secondHalf = new StringBuilder(firstHalf).reverse().toString();
                
                String fullPalindrome;
                if (length % 2 == 0) {
                    fullPalindrome = firstHalf + secondHalf;
                } else {
                    fullPalindrome = firstHalf + secondHalf.substring(1);
                }

                long palNum = Long.parseLong(fullPalindrome);
                String baseK = convertToBaseK(palNum, k);

                if (isPalindrome(baseK)) {
                    sum += palNum;
                    n--;
                    if (n == 0) {
                        return sum;
                    }
                }
            }
            length++;
        }
        return sum;
    }
}
