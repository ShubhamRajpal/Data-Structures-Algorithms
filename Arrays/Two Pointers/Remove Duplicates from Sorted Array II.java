/*
T.C. - O(N)
S.C. - O(1)
Problem Link: https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
*/

class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int cnt = 1, j = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1]) {
                if (cnt < 2) {
                    cnt++;
                    j++;
                    nums[j] = nums[i];
                }
            } else {
                cnt = 1;
                j++;
                nums[j] = nums[i];
            }
        }

        return j + 1;
    }
}
