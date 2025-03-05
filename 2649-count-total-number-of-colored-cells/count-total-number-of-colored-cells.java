class Solution {

    private long solve(long num){
        if(num < 1)  return 0;

        long sum = 0;
        sum = ((2 * (num)) + solve(num-2));

        return sum;
    }

    public long coloredCells(int n) {
        if(n == 1) return 1;
        long start = 2 * n - 1;
        System.out.print(start);
        return start + solve(start-2);
    }
}