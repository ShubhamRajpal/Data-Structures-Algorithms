class Solution {

    // public int longestFibRecurive(int idx, int prevIdx, int prev2Idx, int[] arr)
    // {
    // if (idx == arr.length) {
    // return 0;
    // }

    // int notTake = longestFibRecurive(idx + 1, prevIdx, prev2Idx, arr);
    // int take = 0;
    // if (prev2Idx == -1 || arr[idx] == arr[prevIdx] + arr[prev2Idx]) {
    // take = 1 + longestFibRecurive(idx + 1, idx, prevIdx, arr);
    // }

    // return Math.max(take, notTake);
    // }

    // public int longestFibMemoize(int idx, int prevIdx, int prev2Idx, int[] arr,
    // int[][][] dp) {
    // if (idx == arr.length) {
    // return 0;
    // }

    // if (dp[idx][prevIdx + 1][prev2Idx + 1] != -1)
    // return dp[idx][prevIdx + 1][prev2Idx + 1];

    // int notTake = longestFibMemoize(idx + 1, prevIdx, prev2Idx, arr, dp);
    // int take = 0;
    // if (prev2Idx == -1 || arr[idx] == arr[prevIdx] + arr[prev2Idx]) {
    // take = 1 + longestFibMemoize(idx + 1, idx, prevIdx, arr, dp);
    // }

    // return dp[idx][prevIdx + 1][prev2Idx + 1] = Math.max(take, notTake);
    // }

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
        // return longestFibRecurive(0, -1, -1, arr) < 3 ? 0 : longestFibRecurive(0, -1,
        // -1, arr);

        // Memoization
        // int[][][] dp = new int[n][n + 1][n + 1];
        // for (int it[][] : dp) {
        // for (int it1[] : it) {
        // Arrays.fill(it1, -1);
        // }
        // }
        // return longestFibMemoize(0, -1, -1, arr, dp) < 3 ? 0 : longestFibMemoize(0,
        // -1, -1, arr, dp);

        // Better
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(arr[i], i);
        }

        // Recursive
        // int maxLen = 0;
        // for (int i = 1; i < n; i++) {
        // for (int j = i + 1; j < n; j++) {
        // int len = solve(i, j, arr, indexMap);
        // if (len >= 3) {
        // maxLen = Math.max(maxLen, len);
        // }
        // }
        // }

        // return maxLen;

        // Memoization
        int maxLen = 0;
        int[][] dp = new int[n][n];
        for (int it[] : dp)
            Arrays.fill(it, -1);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = solveMemoize(i, j, arr, indexMap, dp);
                if (len >= 3) {
                    maxLen = Math.max(maxLen, len);
                }
            }
        }

        return maxLen;

        // Tabulation
        // int maxLen = 0;
        // int[][] dp = new int[n][n];

        // for (int i = 0; i < n; i++) {
        //     for (int j = i + 1; j < n; j++) {
        //         int diff = arr[j] - arr[i];
        //         int k = indexMap.getOrDefault(diff, -1);
        //         if (diff < arr[i] && k >= 0) {
        //             dp[i][j] = 1 + dp[k][i];
        //         }else dp[i][j] = 2; 
                
                
        //         maxLen = Math.max(maxLen, dp[i][j]);
        //     }
        // }

        // return maxLen >= 3 ? maxLen : 0;

    }
}