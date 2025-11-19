/*
  Problem Link: https://leetcode.com/problems/keep-multiplying-found-values-by-two/
*/

// Approach-1 (Sort the nums and check if double exists)
// T.C: O(nlogn)
// S.C: O(logn) - additional space used for sorting dependng on implementation
class Solution {
    public int findFinalValue(int[] nums, int original) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == original) {
                original *= 2;
            }
        }

        return original;
    }
}

// Approach-2 (Keep multiplying original by 2 until it exists in set)
// T.C: O(n)
// S.C: O(n)
class Solution {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> st = new HashSet<>();
        for (int i : nums) {
            st.add(i);
        }

        while (st.contains(original)) {
            original *= 2;
        }

        return original;
    }
}
