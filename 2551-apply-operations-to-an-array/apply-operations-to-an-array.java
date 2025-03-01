class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;

        // Brute 3 pass
        //

        // Better 2 pass
        // for (int i = 0; i < n - 1; i++) {
        // if (nums[i] == nums[i + 1]) {
        // nums[i] *= 2;
        // nums[i + 1] = 0;
        // }
        // }

        // int i = 0, j = 0;
        // while (j < n) {
        // if (nums[j] != 0) {
        // int temp = nums[j];
        // nums[j] = nums[i];
        // nums[i] = temp;
        // i++;
        // }
        // j++;
        // }
        // return nums;

        // Optimal
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (i + 1 < n && nums[i] == nums[i + 1] && nums[i] != 0) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }

            if (nums[i] != 0) {
                if (i != j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                j++;
            }
        }

        return nums;
    }
}