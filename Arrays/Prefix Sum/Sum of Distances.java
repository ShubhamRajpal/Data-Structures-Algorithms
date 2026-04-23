/*
  Problem Link: https://leetcode.com/problems/sum-of-distances/
  Similar Problem: LC 1685. Sum of Absolute Differences in a Sorted Array
*/

//Approach (Using prefix sum and map)
//T.C : O(n)
//S.C : O(n)
class Solution {
    public long[] distance(int[] nums) {

        int n = nums.length;
        Map<Integer, Long> indexCount = new HashMap<>();
        Map<Integer, Long> indexSum = new HashMap<>();

        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            long freq = indexCount.getOrDefault(nums[i], 0L);
            long sum = indexSum.getOrDefault(nums[i], 0L);

            ans[i] += freq * i - sum;

            indexCount.put(nums[i], indexCount.getOrDefault(nums[i], 0L) + 1);
            indexSum.put(nums[i], indexSum.getOrDefault(nums[i], 0L) + i);
        }

        indexCount.clear();
        indexSum.clear();

        for (int i = n - 1; i >= 0; i--) {
            long freq = indexCount.getOrDefault(nums[i], 0L);
            long sum = indexSum.getOrDefault(nums[i], 0L);

            ans[i] += sum - freq * i;

            indexCount.put(nums[i], indexCount.getOrDefault(nums[i], 0L) + 1);
            indexSum.put(nums[i], indexSum.getOrDefault(nums[i], 0L) + i);
        }

        return ans;

    }
}
