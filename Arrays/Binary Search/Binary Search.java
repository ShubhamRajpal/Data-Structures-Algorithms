/*
  Problem Link: https://leetcode.com/problems/binary-search/
*/

// Approach-1 (Iterative)
// T.C: O(log N)
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}



// Approach-2 (Recursive)
// T.C: O(log N)
class Solution {
    public int binarySearch(int low, int high, int[] arr, int target) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        if (arr[mid] < target) {
            return binarySearch(mid + 1, high, arr, target);
        }

        return binarySearch(low, mid - 1, arr, target);
    }

    public int search(int[] nums, int target) {
        
        int n = nums.length;
        return binarySearch(0, n - 1, nums, target);

    }
}
