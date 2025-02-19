class Solution {

    private void solve(StringBuilder sb, int n, int k, List<String> ans) {
        if (sb.length() == n) {
            ans.add(sb.toString());
            return;
        }

        for (char curChar = 'a'; curChar <= 'c'; curChar++) {

            if (ans.size() == k) break;

            if (sb.length() == 0 || curChar != sb.charAt(sb.length() - 1)) {
                sb.append(curChar);
                solve(sb, n, k, ans);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public String getHappyString(int n, int k) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        solve(sb, n, k, ans);
        System.out.print(ans);
        return ans.size() < k ? "" : ans.get(k - 1);

    }
}