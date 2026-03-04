/*
  Problem Link: https://leetcode.com/problems/find-in-mountain-array/
*/

// Approach (using peak element concept)
// T.C: O(log n)
// S.C: O(1)

/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {

    public int binarySearchInc(int low, int high, int target, MountainArray mountainArr) {

        while (low <= high) {
            int mid = (low + high) / 2;

            if (mountainArr.get(mid) == target) {
                return mid;
            } else if (mountainArr.get(mid) > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public int binarySearchDec(int low, int high, int target, MountainArray mountainArr) {

        while (low <= high) {
            int mid = (low + high) / 2;

            if (mountainArr.get(mid) == target) {
                return mid;
            } else if (mountainArr.get(mid) > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public int findPeakIndexInMountainArr(MountainArray mountainArr) {

        int n = mountainArr.length();

        int low = 1, high = n - 1;
        while (low <= high) {

            int mid = (low + high) / 2;

            if (mountainArr.get(mid) > mountainArr.get(mid - 1)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {

        int peakIdx = findPeakIndexInMountainArr(mountainArr);

        int ans = binarySearchInc(0, peakIdx, target, mountainArr);
        if (ans != -1) {
            return ans;
        }

        return binarySearchDec(peakIdx, mountainArr.length() - 1, target, mountainArr);
    }
}
