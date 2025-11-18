/*
  Problem Link: https://leetcode.com/problems/1-bit-and-2-bit-characters/description/ 
*/

//Approach-1 (Iterating from left to right)
//T.C : O(n) 
//S.C : O(1)
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;

        while (i < n - 1) {
            i += (bits[i] == 1) ? 2 : 1;
        }

        return i == n - 1;
    }
}


//Approach-2 (Iterating from right to left and counting streak of 1s just after last 0)
//T.C : O(n) 
//S.C : O(1)
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int count1 = 0;

        for (int i = n - 2; i >= 0 && bits[i] == 1; i--) {
            count1++;
        }

        return (count1 % 2 == 0);
    }
}
