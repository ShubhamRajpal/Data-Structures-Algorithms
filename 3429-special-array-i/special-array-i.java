class Solution {
    public boolean isArraySpecial(int[] nums) {
        if(nums.length == 1)  return true;

        for(int j = 1; j < nums.length; j++){
            int xor = nums[j] ^ nums[j-1];
            if((xor & 1) == 0){
                return false;
            }
        }

        return true;
    }
}