/*
  Problem Link: https://leetcode.com/problems/binary-prefix-divisible-by-5/
*/

//Approach (Using bit concept and modular arithmetic)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();

        int cur = 0;
        for(int i : nums){
            cur = ((cur << 1) + i) % 5;
            if(cur % 5 == 0) ans.add(true);
            else ans.add(false);


        }

        return ans;
    }
