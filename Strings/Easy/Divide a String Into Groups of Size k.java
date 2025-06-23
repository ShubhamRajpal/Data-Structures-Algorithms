/*
  Problem Link: https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/description/
*/

//Approach (Simple iterating and generating)
//T.C : O(n)
//S.C : O(k)
class Solution {
    public String[] divideString(String s, int k, char fill) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i += k) {
            int end = Math.min(i + k, n);
            res.add(s.substring(i, end));
        }

        while (res.get(res.size() - 1).length() < k) {
            res.set(res.size() - 1, res.get(res.size() - 1) + fill);
        }
        return res.stream().toArray(String[]::new);
    }
}
