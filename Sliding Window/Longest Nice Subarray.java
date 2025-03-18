/*
  Problem Link: https://leetcode.com/problems/longest-nice-subarray/description/?envType=daily-question&envId=2025-03-18
*/

// Approach 1 (Brute)
// T.C. - O(N^2)
// S.C. - O(1)
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            int res = 0;
            for (int j = i; j < n; j++) {
                if ((nums[j] & res) != 0) break;
                
                maxLen = Math.max(maxLen, j - i + 1);
                res = res | nums[j];
            }
        }

        return maxLen;
    }
}




// Approach 2 (Using binary search on all possible lengths of nice subarrays)
// T.C. - O(N^2 log N)
// S.C. - O(1)
class Solution {

    private boolean isNice(int len, int[] nums) {

        for (int i = 0; i <= nums.length - len; i++) {
            int mask = 0;
            boolean fl = true;
            for (int j = i; j < len + i; j++) {
                if ((nums[j] & mask) != 0) {
                    fl = false;
                    break;
                }
                mask |= nums[j];

            }
            if (fl)
                return true;
        }

        return false;
    }

    public int longestNiceSubarray(int[] nums) {

        int n = nums.length;
        int l = 0, r = n;
        int res = 1;

        while (l <= r) {
            int midLength = l + (r - l) / 2;
            if (isNice(midLength, nums)) {
                res = midLength;
                l = midLength + 1;
            } else {
                r = midLength - 1;
            }
        }

        return res;

    }
}


// Approach 3 (using sliding Window)
// T.C. - O(2*N)
// S.C. - O(1)
class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;
        int maxLen = 1;
        int l = 0, r = 0;
        int mask = 0;
        while (r < n) {

            while ((mask & nums[r]) != 0) {
                mask ^= nums[l];
                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
            mask = mask | nums[r];
            r++;
        }

        return maxLen;
    }
}
