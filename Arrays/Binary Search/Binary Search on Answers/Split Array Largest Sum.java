/*
  Problem Link: https://leetcode.com/problems/split-array-largest-sum/
  Similar Problems - 1. Allocate Minimum Pages - https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
                     2. The Painter's Partition Problem-II - https://www.geeksforgeeks.org/problems/the-painters-partition-problem1535/1          
*/

// Approach (binary search on answers min(max))
// T.C: O(n log(sum - max))
// S.C: O(1)
class Solution {

    public boolean canSplit(int midSum, int[] arr, int k) {
        int n = arr.length;
        int curSubSum = 0, subarraysCnt = 1;

        for (int i = 0; i < n; i++) {
            if (curSubSum + arr[i] <= midSum) {
                curSubSum += arr[i];
            } else {
                subarraysCnt++;
                curSubSum = arr[i];
            }
        }

        return subarraysCnt <= k;
    }

    public int splitArray(int[] nums, int k) {
        int n = nums.length;

        if (n < k)
            return -1;

        int low = Arrays.stream(nums).max().getAsInt();
        int high = Arrays.stream(nums).sum();

        while (low <= high) {

            int mid = (low + high) / 2;

            if (canSplit(mid, nums, k)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
