class Solution {
    public int minimumRecolors(String blocks, int k) {
        int cnt = 0;
        int maxi = 0;
        int n = blocks.length();

        int l = 0, r = 0;
        while (r < n) {
            if(blocks.charAt(r) == 'B') cnt++;

            while (r - l + 1 > k) {
                if (blocks.charAt(l) == 'B') cnt--;
                l++;
            }


            maxi = Math.max(maxi, cnt);
            r++;
        }

        if (maxi >= k) return 0;
        return k - maxi;

    }
}