/*
  Problem Link: https://leetcode.com/problems/divide-array-into-equal-pairs/description/?envType=daily-question&envId=2025-03-17
*/

// Approach 1 (Using sorting)
// T.C. - O(NlogN)
// S.C. - O(1)
class Solution {
    public boolean divideArray(int[] nums) {

        Arrays.sort(nums);
        int cnt = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                cnt++;
            } else {
                if (cnt % 2 != 0) {
                    return false;
                }
                cnt = 1;
            }
        }

        return true;

    }
}


// Approach 2 (Using Map)
// T.C. - O(N)
// S.C. - O(N)
class Solution {
    public boolean divideArray(int[] nums) {

        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() % 2 != 0)
                return false;
        }

        return true;

    }
}
