class Solution {

    private boolean check(int num, String square, int curSum) {
        if (square.length() == 0) {
            return curSum == num;
        }

        for (int i = 0; i < square.length(); i++) {
            if (check(num, square.substring(i + 1), Integer.parseInt(square.substring(0, i + 1)) + curSum))
                return true;
        }

        return false;
    }

    private boolean checkMemo(int i, int num, String sq, int curSum, int [][] memo){
        if(i == sq.length()){
            return curSum == num;
        }

        if(curSum > num) return false;

        if(memo[i][curSum] != -1) return memo[i][curSum] == 1;


        boolean possible = false;
        for(int j = i; j < sq.length(); j++){
            String str = sq.substring(i, j+1);
            int val = Integer.parseInt(str);

            possible = possible || checkMemo(j+1, num, sq, curSum + val, memo);

            if(possible) {
                memo[i][curSum] = 1;
                return true;
            }
        }

        memo[i][curSum] = 0;
        return false;
    }

    public int punishmentNumber(int n) {
        
        // int sum = 0;
        // for(int i = 1; i <= n ; i++){
        //     if(check(i, Integer.toString(i*i), 0)){
        //         sum += i*i;
        //     }
        // }

        // return sum;

        // Memoization
        int res = 0;
        for(int i = 1; i <= n ; i++){
            String sq = Integer.toString(i*i);

            int[][] memo = new int[sq.length()+1][i+1];
            for(int it[] : memo) Arrays.fill(it, -1);

            if(checkMemo(0, i, sq, 0, memo)){
                res += Integer.valueOf(sq);
            }
        }

        return res;
    }
}