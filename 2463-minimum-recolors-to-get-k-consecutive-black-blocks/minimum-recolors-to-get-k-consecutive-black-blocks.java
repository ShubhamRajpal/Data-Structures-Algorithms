class Solution {
    public int minimumRecolors(String blocks, int k) {
        int cnt = 0;
        int maxi = 0;
        int n = blocks.length();
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'B') {
                cnt++;
            }
        }

        if (cnt >= k)
            return 0;

        int l = 0, r = k - 1;
        maxi = cnt;
        while (r < n) {
            r++;

            while (r - l + 1 > k) {
                if (blocks.charAt(l) == 'B')
                    cnt--;
                l++;
            }

            if (r < n && blocks.charAt(r) == 'B')
                cnt++;

            maxi = Math.max(maxi, cnt);
        }

        if (maxi >= k) return 0;
        return k - maxi;

    }
}