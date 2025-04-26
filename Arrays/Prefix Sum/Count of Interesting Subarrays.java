/*
  Problem Link: https://leetcode.com/problems/count-of-interesting-subarrays/
  Similar Problems: Leetcode 974: https://leetcode.com/problems/subarray-sums-divisible-by-k/
*/
//Approach-1 (using 2 loops)
//T.C- O(N^2)
//S.C- O(N)
class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        long[] prefix = new long[n];
        prefix[0] = (nums.get(0) % modulo == k) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            prefix[i] += (nums.get(i) % modulo == k) ? prefix[i - 1] + 1 : prefix[i - 1];
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                long cnt = (i == 0) ? prefix[j] : prefix[j] - prefix[i - 1];
                if ((int) cnt % modulo == k) ans++;
            }
        }

        return ans;
    }
}


//Approach-2 (using prefixSum)
//T.C- O(N)
//S.C- O(N)
class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        long ans = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        int prefixCnt = 0;
        for (int i = 0; i < n; i++) {
            prefixCnt += (nums.get(i) % modulo == k) ? 1 : 0;
            ans += freq.getOrDefault((prefixCnt - k + modulo) % modulo, 0);

            int rem = prefixCnt % modulo;
            freq.put(rem, freq.getOrDefault(rem, 0) + 1);
        }

        return ans;
    }
}
