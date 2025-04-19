/*
  Problem Link: https://leetcode.com/problems/move-zeroes/
*/

// Approach-1(Brute - Using extra Space)
//T.C- O(N)
//S.C- O(N)
class Solution {
    public void moveZeroes(int[] nums) {

        int n = nums.length;
        List<Integer> nonZeros = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0)
                nonZeros.add(nums[i]);
        }

        Arrays.fill(nums, 0);

        for (int i = 0; i < nonZeros.size(); i++) {
            if (nonZeros.get(i) != 0)
                nums[i] = nonZeros.get(i);
        }
    }
}


// Approach-2(Optimal- without extra space)
//T.C- O(N)
//S.C- O(1)
class Solution {
    public void moveZeroes(int[] nums) {

        int j = -1;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] == 0) {
                j = k;
                break;
            }
        }

        if (j == -1) {
            return;
        }

        for (int i = j + 1; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }

    }
}
