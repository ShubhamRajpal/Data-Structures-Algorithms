/*
  Problem Link: https://leetcode.com/problems/subarray-sums-divisible-by-k/
*/

// Approach-1 (Using 3 loops)
//T.C- O(N^3)
//S.C- O(1)
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int K = i; K <= j; K++) {
                    sum += nums[K];
                }
                
                if (sum % k == 0) cnt++;
            }
        }

        return cnt;
    }
}


// Approach-2 (Using 2 loops)
//T.C- O(N^2)
//S.C- O(1)
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum % k == 0) cnt++;
            }
        }

        return cnt;
    }
}


// Approach-3(Using Map and PrefixSum)
//T.C- O(N)
//S.C- O(N)
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0, sum = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];

            int rem = sum % k;
            if (rem < 0) rem += k;
            if (mp.containsKey(rem)) {
                cnt += mp.get(rem);
            }

            mp.put(rem, mp.getOrDefault(rem, 0) + 1);
        }

        return cnt;
    }
}
