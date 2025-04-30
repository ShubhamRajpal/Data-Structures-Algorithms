/* 
  Problem Link: https://leetcode.com/problems/sort-colors/
*/

// Approach-1 : Sorting
// T.C : O(n*log(n))
// S.C : O(1)
class Solution1 {
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
}


// Approach-2 (Using counting)
// T.C : O(n+n)
// S.C : O(1)
class Solution2 {
    public void sortColors(int[] nums) {
        int n = nums.length;

        int count_0 = 0;
        int count_1 = 0;
        int count_2 = 0;

        for (int num : nums) {
            if (num == 0)
                count_0++;
            else if (num == 1)
                count_1++;
            else
                count_2++;
        }

        for (int i = 0; i < n; i++) {
            if (count_0 > 0) {
                nums[i] = 0;
                count_0--;
            } else if (count_1 > 0) {
                nums[i] = 1;
                count_1--;
            } else if (count_2 > 0) {
                nums[i] = 2;
                count_2--;
            }
        }
    }
}


// Approach-3 (Follow up- Dutch National Flag Algorithm) : O(n)
// T.C : O(n)
// S.C : O(1)
class Solution {

    private void swap(int a, int b, int[] nums) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public void sortColors(int[] nums) {
        int n = nums.length;
        int low = 0, mid = 0, high = n - 1;
        while (mid <= high) {

            if (nums[mid] == 0) {
                swap(low, mid, nums);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(mid, high, nums);
                high--;
            }
        }
    }
}
