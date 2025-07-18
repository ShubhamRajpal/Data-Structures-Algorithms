/*
  Problem Link: https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/
*/

// Approach (Use max-heap to keep track of n/3 smallest elements from left and min-heap to keep track of n/3 largest elements from right)
// T.C.- O(nlogn) 
// S.C.- O(n) 
class Solution {
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        long[] part1 = new long[n + 1];
        PriorityQueue<Integer> pq1 = new PriorityQueue<>((a,b) -> b - a);

        long part1MinSum = 0;
        for (int i = 0; i < n; i++) {
            part1MinSum += nums[i];
            pq1.offer(nums[i]);
        }

        part1[0] = part1MinSum;
        for (int i = n; i < 2 * n; i++) {
            part1MinSum += nums[i];
            pq1.offer(nums[i]);
            part1MinSum -= pq1.poll();
            part1[i - (n - 1)] = part1MinSum;
        }

        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        long part2MaxSum = 0;
        for (int i = n * 3 - 1; i >= n*2; i--) {
            part2MaxSum += nums[i];
            pq2.offer(nums[i]);
        }

        // minDiff = part1MinSum(sum of n/3 smallest elements from max-heap) - part2MaxSum(sum of n/3 largest elements from min-heap)
        long minDiff = part1[n] - part2MaxSum;

        for (int i = (n*2) - 1; i >= n; i--) {
            part2MaxSum += nums[i];
            pq2.offer(nums[i]);
            part2MaxSum -= pq2.poll();
            minDiff = Math.min(minDiff, part1[i-n]-part2MaxSum);
        }

        return minDiff;

    }
}
