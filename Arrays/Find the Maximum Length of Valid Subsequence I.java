/*
  Problem Link: https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-i/
*/

//Approach (observing the condition and checking, oddCount, evenCount and alternatingCount odd/even
//T.C : O(n)
//S.C : O(1)

class Solution {
    public int maximumLength(int[] nums) {
        int alternatingCount = 0, prevInd = -1;
        int n = nums.length;
        int evenCnt = 0, oddCnt = 0;
        for(int i = 0; i < n; i++){
            evenCnt += nums[i] % 2 == 0 ? 1 : 0;
            oddCnt += nums[i] % 2 == 1 ? 1 : 0;

            if(prevInd == -1 || (nums[prevInd] % 2 == 0 && nums[i] % 2 == 1) || (nums[prevInd] % 2 == 1 && nums[i] % 2 == 0)){
                alternatingCount++;
                prevInd = i;
            }
        }

        int sameParityCnt = Math.max(evenCnt, oddCnt);

        return Math.max(sameParityCnt, alternatingCount);

    }
}
