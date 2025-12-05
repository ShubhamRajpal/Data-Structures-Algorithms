/*
  Problem Link: https://leetcode.com/problems/count-partitions-with-even-sum-difference/
*/

// Approach-1 (Maintain sum of left and right part for each partiton)
// T.C : O(n)
// S.C : O(1)
class Solution {
    public int countPartitions(int[] nums) {
        
        int n = nums.length;
        int rightSum = Arrays.stream(nums).sum();

        int leftSum = 0;
        int partitions = 0;
        for(int i = 0; i < n-1; i++){
            int diff = rightSum - leftSum;
            if(diff % 2 == 0){
                partitions++;
            }
            leftSum += nums[i];
            rightSum -= nums[i];
        }

        return partitions;
    }
}


// Approach-2 (Using maths : diff of left and right part can only be even if totalSum is even)
// T.C : O(n) (single Pass)
// S.C : O(1)
class Solution {
    public int countPartitions(int[] nums) {
        
        int n = nums.length;
        int totalSum = Arrays.stream(nums).sum();

        return totalSum % 2 == 0 ? n - 1 : 0;
    }
}
