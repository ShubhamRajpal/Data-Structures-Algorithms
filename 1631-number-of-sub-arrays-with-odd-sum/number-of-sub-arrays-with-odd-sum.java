class Solution {
    public int numOfSubarrays(int[] arr) {
        int n = arr.length;
        int[] prefixSum = new int[n];
        int MOD = (int) (1e9 + 7);

        prefixSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        int oddCnt = 0, evenCnt = 1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (prefixSum[i] % 2 == 0) {
                res = (res+oddCnt)%MOD;
                evenCnt++;
            } else {
                res = (res+evenCnt)%MOD;
                oddCnt++;
            }
        }

        return res % MOD;
    }
}