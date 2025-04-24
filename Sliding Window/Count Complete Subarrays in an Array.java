/*
  Problem Link: https://leetcode.com/problems/count-complete-subarrays-in-an-array/description/
*/

//Approach (Sliding Window)
//T.C- O(N)
//S.C- O(N)
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        int l = 0, r = 0, cnt = 0;
        Set<Integer> st = new HashSet<>();
        for (int i : nums) {
            st.add(i);
        }

        int totalDistinct = st.size();
        Map<Integer, Integer> mp = new HashMap<>();
        while (r < n) {
            mp.put(nums[r], mp.getOrDefault(nums[r], 0) + 1);

            while (l <= r && mp.size() == totalDistinct) {
                cnt += (n - r);
                mp.put(nums[l], mp.getOrDefault(nums[l], 0) - 1);
                if (mp.get(nums[l]) == 0)
                    mp.remove(nums[l]);
                l++;
            }

            r++;
        }

        return cnt;
    }
}
