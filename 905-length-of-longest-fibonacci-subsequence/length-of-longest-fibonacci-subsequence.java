class Solution {

    public int solveRecursive(int prevIdx, int curIdx, int[] arr, Map<Integer, Integer> indexMap) {

        int diff = arr[curIdx] - arr[prevIdx];
        if (indexMap.containsKey(diff) && indexMap.get(diff) < prevIdx) {
            int i = indexMap.get(diff);
            return 1 + solveRecursive(i, prevIdx, arr, indexMap);
        }

        return 2;
    }

    public int solveMemoize(int prevIdx, int curIdx, int[] arr, Map<Integer, Integer> indexMap, int[][] dp) {

        if (dp[prevIdx][curIdx] != -1)
            return dp[prevIdx][curIdx];

        int diff = arr[curIdx] - arr[prevIdx];
        if (indexMap.containsKey(diff) && indexMap.get(diff) < prevIdx) {
            int i = indexMap.get(diff);
            return dp[prevIdx][curIdx] = 1 + solveMemoize(i, prevIdx, arr, indexMap, dp);
        }

        return dp[prevIdx][curIdx] = 2;
    }

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;

        // Better
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(arr[i], i);
        }

        // Recursive
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = solveRecursive(i, j, arr, indexMap);
                if (len >= 3) {
                    maxLen = Math.max(maxLen, len);
                }
            }
        }

        return maxLen >= 3 ? maxLen : 0;

        // Memoization
        // int maxLen = 0;
        // int[][] dp = new int[n][n];
        // for (int it[] : dp)
        //     Arrays.fill(it, -1);

        // for (int i = 0; i < n; i++) {
        //     for (int j = i + 1; j < n; j++) {
        //         int len = solveMemoize(i, j, arr, indexMap, dp);
        //         maxLen = Math.max(maxLen, len);
        //     }
        // }

        // return maxLen >= 3 ? maxLen : 0;

        // Tabulation
        // int maxLen = 0;
        // int[][] dp = new int[n][n];

        // for (int i = 0; i < n; i++) {
        //     for (int j = i + 1; j < n; j++) {
        //         int diff = arr[j] - arr[i];
        //         int k = indexMap.getOrDefault(diff, -1);
        //         if (k < i && k >= 0) {
        //             dp[i][j] = 1 + dp[k][i];
        //         } else
        //             dp[i][j] = 2;

        //         maxLen = Math.max(maxLen, dp[i][j]);
        //     }
        // }

        // return maxLen >= 3 ? maxLen : 0;

    }
}