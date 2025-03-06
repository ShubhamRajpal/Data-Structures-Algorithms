class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        
        int n = grid.length;
        int size = n*n;
        int originalSum = (size * (size+1))/2;
        Map<Integer,Integer> freq = new HashMap<>();
        // for(int i = 1; i <= size; i++){
        //     freq.put(i, freq.getOrDefault(i,0)+1);
        // }
        int repeated = 0, curSum = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                freq.put(grid[i][j], freq.getOrDefault(grid[i][j],0)+1);
                if(freq.get(grid[i][j]) == 2){
                    repeated = grid[i][j];
                }

                curSum += grid[i][j];
            }
        }

        curSum  = curSum - repeated;
        int missing  = originalSum - curSum;

        return new int[]{repeated, missing};

    }
}