/*
  Problem Link: https://leetcode.com/problems/longest-harmonious-subsequence/
*/

//Approach-1 (Using sorting and sliding window)
//T.C : O(nlogn)
//S.C : O(1)
class Solution {
    public int findLHS(int[] nums) {

        int n = nums.length;
        int maxLen = 0;

        Arrays.sort(nums);
        int i = 0;
        for(int j = 1; j < n;){
            while(i < j && nums[i] < nums[j] && nums[j] - nums[i] != 1){
                i++;
            }
            
            if(nums[i] < nums[j] && nums[j] - nums[i] == 1){
                maxLen = Math.max(maxLen, j - i + 1);
            }
            j++;
        }

        return maxLen == 1 ? 0 : maxLen;
    }   
}


//Approach-2 (Using map and counting)
//T.C : O(n)
//S.C : O(n)
class Solution {
    public int findLHS(int[] nums) {

        int n = nums.length;
        int maxLen = 0;

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i : nums) {
            freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
        }

        for (int num : nums) {

            if (freqMap.containsKey(num + 1)) {
                maxLen = Math.max(maxLen, freqMap.get(num) + freqMap.get(num + 1));
            }
        }

        return maxLen;
    }
}
