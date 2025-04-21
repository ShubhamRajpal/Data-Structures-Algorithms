/*
  Problem Link: https://leetcode.com/problems/count-the-hidden-sequences/description/
*/
//Approach - Using Maths for counting total possible shifts
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int n = differences.length;
        int curr = 0; // Simulates building the array starting from a[0] = 0
        int minVal = 0, maxVal = 0;
        for(int i = 0; i < n; i++){
            curr = differences[i] + curr;

            minVal = Math.min(minVal, curr);
            maxVal = Math.max(maxVal, curr);

            if(((upper - maxVal) - (lower - minVal) + 1) <= 0) return 0;
        }

        return (upper - maxVal) - (lower - minVal) + 1;
    }
}
