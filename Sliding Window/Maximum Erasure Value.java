/*
  Problem Link: https://leetcode.com/problems/maximum-erasure-value/
*/

// Approach (Using sliding window + 2 pointers)
// T.C: O(n)
// S.C: O(n)
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int maxScore = 0;

        Map<Integer, Integer> indexMap = new HashMap<>();
        int i = 0, j = 0;
        int sum = 0;

        while (j < n) {

            while (i < n && indexMap.containsKey(nums[j])) {
                sum -= nums[i];
                indexMap.remove(nums[i]);
                i++;
            }

            indexMap.put(nums[j], j);
            sum += nums[j];

            maxScore = Math.max(maxScore, sum);
            j++;

        }

        return maxScore;

    }
}
