/*
  Problem Link: https://leetcode.com/problems/power-of-four/
*/

//Approach-1 (Simplest for loop)
//T.C : log(n) to base 4
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
}


//Approach-2 (Using Maths)
//T.C : O(log(a)) -> pow(4, a),the exponentiation operation still requires logarithmic time with respect to the value of a.
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        
        int a = (int) (Math.log(n) / Math.log(4));
        /*
            4^a = n
            a = log(n) to base 4
            a = log(n) to base e / log(4) to base e
        */
        
        return a == (int)a;
    }
}


//Approach-3 (Using Bit and Maths)
//T.C : O(1)
class Solution {
    public boolean isPowerOfFour(int n) {
       /*
          num & (num - 1) == 0 : This checks if the number is a power of 2. Every number that is a power of 4 i also a power of 2.
          (n - 1) % 3          : All numbers that are powers of 4 have this property. For example, 4^1 - 1 = 3, 4^2 - 1 = 15, 4^3 - 1 = 63, and so on.
          Maths proof of (n-1)%3 == 0 (using PMI)
          Consider three consecutive numbers: n, n+1, and n+2, where n is any integer.
          If n is a multiple of 4, then (4^k-1)%3 == 0  --> (1)
          If n is one more than a multiple of 3, i.e., n=3k+1 for some integer k, 
          then (4^k+1 - 1) must also be divisble by 3.
          Put k = k+1 in eqn. 1
          [(4^k+1) - 1] => [(4^k . 4) - 1] => [(3x+1)*4 - 1] => 3[4x+1] which is also divisible by 3. Similarly for k = k+2, this holds true
      */
        return n > 0 && (n & (n - 1)) == 0 && (n - 1) % 3 == 0;
    }
}

//Approach-4 (Using Maths: 1-Liner)
//T.C : O(1)
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) return false;
        return ((n-1)%3 == 0) && (1073741824 % n) == 0;
    }
}
