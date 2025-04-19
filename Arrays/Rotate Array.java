/*
  Problem Link: https://leetcode.com/problems/rotate-array/description/
*/

// Approach-1 (Brute)
// T.C. - O(N+k)
// S.C. - O(k)
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] temp = new int[k];
        for(int i = (n-k); i < n; i++){
            temp[i-(n-k)] = nums[i];
        }


        for(int i = n-k-1;i >= 0; i--){
            nums[i+k] = nums[i];
        }

        for(int i = 0;i < k; i++){
            nums[i] = temp[i];
        }
    }
}

// Approach-2 (Optimal)
// T.C. - O(N)
// S.C. - O(1)
class Solution {

    public void reverse(int[] nums, int s, int e){

        while(s <= e){
            int temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;
            s++;
            e--;
        }
    }
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0 , n - 1);


        /*
        Just for knowledge
          //left rotate
          reverse(nums, 0, k);
          reverse(nums, k+1, n-1);
          reverse(nums, 0, n-1);
        */
    }
}
