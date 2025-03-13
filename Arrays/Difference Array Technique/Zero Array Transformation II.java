/*
  Problem Link: https://leetcode.com/problems/zero-array-transformation-ii/description/?envType=daily-question&envId=2025-03-13
*/


// Approach 1 (using Diff Array with Linear Search)
// T.C. - O(Q * (Q+N))
// S.C. - O(N)
class Solution {

    private boolean checkDifference(int i, int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n];

        for (int j = 0; j <= i; j++) {
            int s = queries[j][0];
            int e = queries[j][1];
            int val = queries[j][2];

            diff[s] += val;
            if (e + 1 < n) {
                diff[e + 1] -= val;
            }
        }

        int cumSum = 0;
        for (int k = 0; k < n; k++) {
            cumSum += diff[k];
            diff[k] = cumSum;

            if (nums[k] - diff[k] > 0) {
                return false;
            }
        }

        return true;

    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;

        if (Arrays.stream(nums).allMatch(num -> num == 0)) {
            return 0;
        }

        for (int i = 0; i < q; i++) {
            if (checkDifference(i, nums, queries)) {
                return i + 1;
            }
        }

        return -1;
    }
}



// Approach 2 (using Diff Array with Binary Search)
// T.C. - O(log (Q+N))
// S.C. - O(N)
class Solution {

    private boolean checkDifference(int i, int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n];

        for (int j = 0; j <= i; j++) {
            int s = queries[j][0];
            int e = queries[j][1];
            int val = queries[j][2];

            diff[s] += val;
            if (e + 1 < n) {
                diff[e + 1] -= val;
            }
        }

        int cumSum = 0;
        for (int k = 0; k < n; k++) {
            cumSum += diff[k];
            diff[k] = cumSum;

            if (nums[k] - diff[k] > 0) {
                return false;
            }
        }

        return true;

    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;

        if (Arrays.stream(nums).allMatch(num -> num == 0)) {
            return 0;
        }

        int l = 0, r = q - 1, ind = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (checkDifference(mid, nums, queries)) {
                r = mid - 1;
                ind = mid + 1;
            } else {
                l = mid + 1;
            }
        }

        return ind;
    }
}
