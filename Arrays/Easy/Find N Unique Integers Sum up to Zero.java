/*
  Problem Link: https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
*/

//Approach-1 (Iterate linearly)
//T.C : O(n) 
//S.C : O(1)
class Solution {
    public int[] sumZero(int n) {
        int[] result = new int[n];

        int start = 1;
        int i = 0;
        while (i + 1 < n) {
            result[i] = start;
            result[i + 1] = -start;

            i += 2;
            start++;
        }

        return result;
    }
}


//Approach-2 (Two pointers)
//T.C : O(n) 
//S.C : O(1)
class Solution {
    public int[] sumZero(int n) {
        int[] result = new int[n];

        int i = 0;
        int j = n - 1;
        int start = 1;
        while (i < j) {
            result[i] = start;
            result[j] = -start;

            start++;
            i++;
            j--;
        }

        return result;
    }
}
