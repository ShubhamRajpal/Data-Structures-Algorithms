/*
  Problem Link: https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
*/


// Approach (Iterate for all nums till n)
// T.C: O(nlogn)
// S.C: O(1)
class Solution {
    public boolean isValid(int num) {
        int temp = num;
        while (temp != 0) {
            if (temp % 10 == 0) {
                return false;
            }
            temp /= 10;
        }
        return true;
    }

    public int[] getNoZeroIntegers(int n) {
        int num = 1;
        int a = -1, b = -1;
        while (num < n) {
            a = num;
            b = n - a;

            if (isValid(a) && isValid(b)) {
                break;
            }
            num++;
        }

        return new int[] { a, b };
    }
}
