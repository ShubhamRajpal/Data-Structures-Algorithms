/*
   Problem Link: https://leetcode.com/problems/bitwise-ors-of-subarrays/
*/
//Approach (using hashset to store previous or values)
//T.C : O(n*32) ~ O(n)
//S.C : O(n*32) ~ O(n)
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        Set<Integer> prev = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        Set<Integer> res = new HashSet<>();
       
       
        for (int i = 0; i < n; i++) {

            for (Integer a : prev) {
                int val = a | arr[i];
                cur.add(val);
                res.add(val);
            }

            cur.add(arr[i]);
            res.add(arr[i]);
            prev = cur;
            cur = new HashSet<>();
        }

        return res.size();
    }
}
