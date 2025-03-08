/*
  Problem Link: https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/?envType=daily-question&envId=2025-03-08
*/


//Approach-1 (Brute Force)
//T.C : O(n*k)
//S.C : O(1)
class Solution {
    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int result = k;

        for (int i = 0; i <= n - k; i++) {
            int W = 0;
            for (int j = i; j - i + 1 <= k; j++) {
                if (blocks.charAt(j) == 'W') {
                    W++;
                }
            }
            result = Math.min(result, W);
        }

        return result;
    }
}


// Optimal (Using Sliding Window)
// T.C : O(n)
// S.C : O(1)
class Solution {
    public int minimumRecolors(String blocks, int k) {

        int cnt = 0;
        int maxi = 0;
        int n = blocks.length();

        int l = 0, r = 0;
        while (r < n) {
            if (blocks.charAt(r) == 'B')
                cnt++;

            while (r - l + 1 > k) {
                if (blocks.charAt(l) == 'B')
                    cnt--;
                l++;
            }

            maxi = Math.max(maxi, cnt);
            r++;
        }

        if (maxi >= k) return 0;
        return k - maxi;

    }
}
