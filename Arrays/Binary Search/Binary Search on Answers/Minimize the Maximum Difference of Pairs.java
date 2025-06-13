//Binary Search + Greedy (This is the same Qn of pattern "Applying binary search on answer" (Time Compplexity - O(m * log(n)) where m = max diff in pair
//How to identify -> Notice the keywords - "min max"
/*
    Whenever we see in Question to Find Min(Max) or Max(Min) 
    we will try to use Binary search on the result
*/
/*
    Problem Link:  https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/: 
*/

//T.C : O(nlogn)
//S.c : O(1)
class Solution {
    private boolean isPossible(int[] nums, int maxDiff, int p) {
        int pairs = 0;
        int n = nums.length;
        for (int i = 0; i <= n - 2;) {
            if (Math.abs(nums[i] - nums[i + 1]) <= maxDiff) {
                pairs++;
                i += 2;
            } else {
                i++;
            }
        }

        return pairs >= p;
    }

    public int minimizeMax(int[] nums, int p) {
        int n = nums.length;

        Arrays.sort(nums);
        int low = 0;
        int high = nums[n - 1] - nums[0];

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(nums, mid, p)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;

    }
}
