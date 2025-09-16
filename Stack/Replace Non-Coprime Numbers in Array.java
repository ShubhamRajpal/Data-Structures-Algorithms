/*
  Problem Link: https://leetcode.com/problems/replace-non-coprime-numbers-in-array/
*/

//Approach (Using arraylist as a stack)
//T.C : O(n * log(x)), where log comes from GCD
//S.C : O(1)
class Solution {

    // Euclidean algorithm for GCD
    public int findGCD(int a, int b) {

        return b == 0 ? a : findGCD(b, a % b);
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            ans.add(num);
            // Keep merging while the last number and current num are non-coprime
            while (ans.size() > 1) {
                int c = ans.get(ans.size() - 1);
                int d = ans.get(ans.size() - 2);
                int gcd = findGCD(c, d);
                if (gcd == 1) // coprime, stop merging
                    break;
                
                ans.remove(ans.size() - 1);
                ans.remove(ans.size() - 1);
                int LCM = (c / gcd) * d; // for avoiding integer overflow or use long
                ans.add( LCM);
            }
        }
        return ans;

    }
}
