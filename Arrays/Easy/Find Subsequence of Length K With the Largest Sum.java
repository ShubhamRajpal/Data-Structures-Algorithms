/*
  Problem Link: https://leetcode.com/problems/find-subsequence-of-length-k-with-the-largest-sum
*/

//Approach-1 (Using sorting with indices)
//T.C : O(nlogn)
//S.C : O(n)
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int[] res = new int[k];
        int n = nums.length;

        int[][] tempArr = new int[n][2];
        for (int i = 0; i < n; i++) {
            tempArr[i][0] = nums[i];
            tempArr[i][1] = i;
        }

        Arrays.sort(tempArr, (a, b) -> b[0] - a[0]);

        Arrays.sort(tempArr, 0, k, (a, b) -> a[1] - b[1]);

        for (int i = 0; i < k; i++) {
            res[i] = tempArr[i][0];
        }

        return res;

    }
}

//NOTE :  Java doesn't have nth_element (partial sort) utility.
//Approach-2 (Using nth_element for partial sort)
//T.C : O(nlogn)
//S.C : O(1)
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        if (k == nums.length)
            return nums;

        // Copy nums to a temp array to find k largest elements
        int[] temp = Arrays.copyOf(nums, nums.length);

        // Use a partial sort to place the k largest elements in the first k slots (unordered)
        Arrays.sort(temp); // Java doesn't have nth_element, so sort entire array (O(n log n) worst-case)
        int threshold = temp[temp.length - k]; // k-th largest

        // Count how many times threshold appears in the top-k largest
        int countThreshold = 0;
        for (int i = temp.length - k; i < temp.length; i++) {
            if (temp[i] == threshold)
                countThreshold++;
        }

        int[] result = new int[k];
        int index = 0;

        for (int num : nums) {
            if (num > threshold) {
                result[index++] = num;
            } else if (num == threshold && countThreshold > 0) {
                result[index++] = num;
                countThreshold--;
            }
            if (index == k)
                break;
        }

        return result;
    }
}
