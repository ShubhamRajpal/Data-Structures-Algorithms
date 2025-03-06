class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {

        int n = grid.length;
        // int size = n*n;
        // int originalSum = (size * (size+1))/2;
        // Map<Integer,Integer> freq = new HashMap<>();
        // int repeated = 0, curSum = 0;
        // for(int i = 0; i < n; i++){
        // for(int j = 0; j < n; j++){
        // freq.put(grid[i][j], freq.getOrDefault(grid[i][j],0)+1);
        // if(freq.get(grid[i][j]) == 2){
        // repeated = grid[i][j];
        // }

        // curSum += grid[i][j];
        // }
        // }

        // curSum = curSum - repeated;
        // int missing = originalSum - curSum;

        // return new int[]{repeated, missing};

        // Approach 2 Using Mathematical Equation
        long size = n * n;

        long gridSum = 0, gridSqSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gridSum += grid[i][j];
                gridSqSum += (grid[i][j] * grid[i][j]);
            }
        }

        long originalSum = (size * (size + 1)) / 2;
        long originalSqSum = (size * (size + 1) * (2 * size + 1)) / 6;

        long sqDiff = gridSqSum - originalSqSum;
        long sumDiff = gridSum - originalSum;

        int repeated =  (int)(sqDiff / sumDiff + sumDiff) / 2;
        int missing =  (int)(sqDiff / sumDiff - sumDiff) / 2;

        return new int[] {repeated, missing};

    }
}