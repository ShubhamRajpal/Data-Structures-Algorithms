class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        // for (int i = 0; i < n - 1; i++) {
        // if (nums[i] == nums[i + 1]) {
        // nums[i] *= 2;
        // nums[i + 1] = 0;
        // }
        // }

        // int i = 0;
        // for (int j = 0; j < n; j++) {
        // if (nums[j] != 0) {
        // nums[i] = nums[j];
        // i++;
        // }
        // }

        // for (int j = i; j < n; j++) {
        // nums[j] = 0;
        // }
        // return nums;

        // Better
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]){
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }

        int i = 0, j = 0;
        while(j < n){
            if(nums[j] != 0){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
            }
            j++;
        }
         return nums;

        // Optimal
        // boolean fl = false;
        // for (int i = 0; i < n - 1; i++) {
        //     if (nums[i] == nums[i + 1] && nums[i] != 0) {
        //         fl = true;
        //         nums[i] *= 2;
        //         nums[i + 1] = 0;
        //     }

        //     int j = i + 2;
        //     while (j < n && nums[j] != 0) {
        //         if (fl) {
        //             int temp = nums[j];
        //             nums[j] = nums[j - 1];
        //             nums[j - 1] = temp;
        //         }
        //         j++;
        //     }
        //     fl = false;
        // }

        // return nums;
    }
}