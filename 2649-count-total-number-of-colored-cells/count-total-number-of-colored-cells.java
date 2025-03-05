class Solution {

    private long solve(long num){
        if(num < 1)  return 0;

        long sum = 0;
        sum = ((2 * (num)) + solve(num-2));

        return sum;
    }

    public long coloredCells(int n) {
        // if(n == 1) return 1;
        // long start = 2 * n - 1;
        // System.out.print(start);
        // return start + solve(start-2);

        // Approach 2 Iterative
        // long sum = 1;
        // for(int i = 2; i <= n; i++){
        //     sum += 4*(i-1);
        // }

        // return sum;

        //Appraoch 3
        return 1 + (long)n*(n-1)*2;
    }
}