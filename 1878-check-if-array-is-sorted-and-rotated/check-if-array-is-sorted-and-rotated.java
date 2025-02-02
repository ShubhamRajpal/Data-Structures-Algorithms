class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int[] sorted = new int[n];
        for(int r = 0; r <= n; r++){
            int idx = 0;
            for(int i = r; i < n; i++){
                sorted[idx] = nums[i];
                idx++;
            }

            for(int  i = 0; i < r; i++){
                sorted[idx] = nums[i];
                idx++;
            }

            boolean isSorted = true;
            for(int i = 0; i < n-1; i++){
                if(sorted[i] > sorted[i+1]){
                    isSorted = false;
                    break;
                }
            }

            if(isSorted) return true;

        }

        return false;
    }
}