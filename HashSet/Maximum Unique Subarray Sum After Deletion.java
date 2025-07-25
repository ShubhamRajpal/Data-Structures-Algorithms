/*
  Problem Link: https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion/
*/

// Approach (using Set)
// S.C: O(n)
// T.C: O(n)
class Solution {
    public int maxSum(int[] nums) {
        int n = nums.length;
        Set<Integer> st = new HashSet<>();
        int sum = 0, maxi = -101;
        for (int i : nums) {
            if (i >= 0){
                st.add(i);
            }
            maxi = Math.max(maxi, i);
        }

        if (st.isEmpty()){
            return maxi;
        }

        for (int i : st) {
            sum += i;
        }

        return sum;
    }
}
