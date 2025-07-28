/*
  Problem Link: https://leetcode.com/problems/count-special-triplets/description/
*/

// Approach (For every value at index i, precompute count of (2 * value) in prefix and suffix part of index i)
// T.C: O(n)
// S.C: O(n)
class Solution {
    public int specialTriplets(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[100001];
        int[] suffix = new int[100001];

        for (int i : nums) {
            suffix[i]++;
        }
        
        long cnt = 0;
        int mod = (int) 1e9 + 7;
        for (int i : nums) {
            suffix[i]--;
            int val = i * 2;
            if (val < 100001) {
                cnt = (cnt + (long) prefix[val] * (long) suffix[val]) % mod;
            }
            prefix[i]++;
        }

        return (int) cnt;
    }
}
