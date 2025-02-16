class Solution {

    private boolean solve(int idx, int[] avail, int n, int[] ans) {
        if (idx == ans.length)
            return true;

        if (ans[idx] != 0) {
            return solve(idx + 1, avail, n, ans);
        }

        for (int num = n; num >= 1; num--) {

            if (avail[num] == 1) continue;

            ans[idx] = num;
            avail[num] = 1;

            if (num == 1) {
                if (solve(idx + 1, avail, n, ans)) {
                    return true;
                }
            } else {
                if (idx + num < ans.length && ans[idx + num] == 0) {
                    ans[idx + num] = num;

                    if (solve(idx + 1, avail, n, ans))
                        return true;

                    ans[idx + num] = 0;
                }
            }

            ans[idx] = 0;
            avail[num] = -1;

        }
        return false;
    }

    public int[] constructDistancedSequence(int n) {
        int[] ans = new int[2 * n - 1];
        int[] avail = new int[n + 1];
        Arrays.fill(avail, -1);

        solve(0, avail, n, ans);
        return ans;

    }
}