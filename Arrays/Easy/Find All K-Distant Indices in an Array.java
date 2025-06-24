/*
  Problem Link: https://leetcode.com/problems/find-all-k-distant-indices-in-an-array
*/

//Approach-1 (Simply iterate on all pairs(i,j) and find valid indices. 
//T.C : O(n^2), we visit every index only n times.
//S.C : O(1)
class Solution {

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        // Traverse number pairs
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (nums[j] == key && Math.abs(i - j) <= k) {
                    res.add(i);
                    break; // early termination to prevent duplicate addition
                }
            }
        }
        return res;
    }
}



//Approach-2 (Simply iterate and find valid indices. Just be careful of corner cases and overlapping indices
//T.C : O(n), we visit every index only 2 times.
//S.C : O(1)
class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        for (int j = 0; j < n; j++) {
            if (nums[j] == key) {
                int start = Math.max(j - k, 0);
                int end = Math.min(j + k, n - 1);

                // Avoid adding duplicates
                if (!result.isEmpty() && result.get(result.size() - 1) >= start) {
                    start = result.get(result.size() - 1) + 1;
                }

                for (int i = start; i <= end; i++) {
                    result.add(i);
                }
            }
        }

        return result;
    }
}
