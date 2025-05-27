/*
  Problem Link: https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/
*/

//Approach - Constant time using maths
//T.C : O(1)
//S.C : O(1)
class Solution {
    public int differenceOfSums(int n, int m) {
        int totalSum = (n * (n + 1)) / 2;
        int k = n / m;
        int divisibleSum = m * k * (k + 1) / 2;
        int nonDivisbleSum = totalSum - divisibleSum;

        return nonDivisbleSum - divisibleSum;
    }
}
