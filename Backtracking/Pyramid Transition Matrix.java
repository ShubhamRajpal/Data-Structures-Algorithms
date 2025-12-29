/*
  Problem Link: https://leetcode.com/problems/pyramid-transition-matrix/
*/

//Approach (Khandani Backtracking template -> all possible options)
//T.C : ~(L^n) , L = maximum count of top characters available for each pairs in allowed, n = bottom.length()
//S.C : O(n^2) recursion stack can go at most n levels deep, and at each level you keep a partially built row of length ≤ n, so the total stack memory is O(n × n) = O(n²).
class Solution {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
      
        // Build mapping from pair -> list of possible top blocks
        Map<String, List<Character>> mp = new HashMap<>();
        for (String pattern : allowed) {
            String pair = pattern.substring(0, 2);
            char top = pattern.charAt(2);
            mp.computeIfAbsent(pair, k -> new ArrayList<>()).add(top);
        }

         Map<String, Boolean> dp = new HashMap<>();

        return solve(bottom, mp, 0, new StringBuilder(), dp);
    }

    private boolean solve(String curr, Map<String, List<Character>> mp, int idx, StringBuilder above, Map<String, Boolean> dp) {
        if (curr.length() == 1) {
            // Pyramid is complete
            return true;
        }

        String key = curr + "_" + idx + "_" + above.toString();
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        if (idx == curr.length() - 1) {
            // Finished building current above row; move up
            boolean result = solve(above.toString(), mp, 0, new StringBuilder(), dp);
            dp.put(key, result);
            return result;
        }

        String pair = curr.substring(idx, idx + 2);
        if (!mp.containsKey(pair)) {
            dp.put(key, false);
            return false;
        }

        for (char ch : mp.get(pair)) {
            above.append(ch); // DO
            if (solve(curr, mp, idx + 1, above, dp)) { // EXPLORE
                dp.put(key, true);
                return true;
            }
            above.deleteCharAt(above.length() - 1); // UNDO
        }

        dp.put(key, false);
        return false;
    }
}
