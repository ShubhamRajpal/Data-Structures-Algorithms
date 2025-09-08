/*
  Problem Link: https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
*/


// Approach (Iterate for all nums till n-1)
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



// Approach-2 (Fixing each digit and forming a and b digit by digit)
// T.C : O(logn)
// S.C : O(1)
class Solution {
    public int[] getNoZeroIntegers(int n) {
        int a = n, b = 0;
        int temp = a, placeVal = 1;
        while (temp > 1) {
            int diff = 1;
            if (temp % 10 == 1) {
                diff = 2;
            }

            a = a - diff * placeVal;
            b = b + diff * placeVal;
            temp -= diff;
            placeVal *= 10;
            temp /= 10;
        }

        return new int[] { a, b };
    }
}
