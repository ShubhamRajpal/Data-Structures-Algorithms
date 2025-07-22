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


//Approach-2 (Using cumulative sum to find subarray sum)
//T.C : O(n)
//S.C : O(n)
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> indexMap = new HashMap<>();

        int[] prefixSum = new int[n];
        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        
        int i = 0, j = 0;
        int maxScore = 0;
        while (j < n) {
            if (indexMap.containsKey(nums[j])) {
                i = Math.max(i, indexMap.get(nums[j]) + 1);
            }

            indexMap.put(nums[j], j);
            int sum = (i == 0) ? prefixSum[j] : prefixSum[j] - prefixSum[i - 1];
            maxScore = Math.max(maxScore, sum);
            j++;

        }

        return maxScore;

    }
}
