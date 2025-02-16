class Solution {

    private boolean check(int i, String square, int curSum, int target){

        if(i == square.length()){
            return curSum == target;
        }

        if(curSum >  target) return false;

        for(int j = i;j < square.length(); j++){
            
            String substr = square.substring(i, j+1);
            int val = Integer.parseInt(substr);
            if(check(j+1, square, curSum+val, target)){
                return true;
            }
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

            // Add value of subtring[i..j] to curSum
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

    private boolean checkOptimal(int curNum, int curSum, int num){
        if(curNum == 0){
            return curSum == num;
        }

        return checkOptimal(curNum/10, curSum + (curNum % 10), num) ||
                checkOptimal(curNum/100, curSum + (curNum % 100), num) ||
                checkOptimal(curNum/1000, curSum + (curNum % 1000), num) ||
                checkOptimal(curNum/10000, curSum + (curNum % 10000), num);
    }

    public int punishmentNumber(int n) {
        
        int sum = 0;
        for(int i = 1; i <= n ; i++){
            if(check(0, Integer.toString(i*i), 0, i)){
                sum += i*i;
            }
        }

        return sum;

        // Approach-1 : (Recursion + Memoization)
        // T.C : O(n * 2^(log10(n^2)))
        // S.C : O(n * log10(n^2))
        // int res = 0;
        // for(int i = 1; i <= n ; i++){
        //     String sq = Integer.toString(i*i);

        //     int[][] memo = new int[sq.length()+1][i+1];
        //     for(int it[] : memo) Arrays.fill(it, -1);

        //     if(checkMemo(0, i, sq, 0, memo)){
        //         res += Integer.valueOf(sq);
        //     }
        // }

        // return res;

        // Approach-2 : (Recursion + Memoization)
        // T.C : O(n * 2^(log10(n^2)))
        // S.C : O(log10(n^2))

        // int sum = 0;
        // for(int i = 1;i <= n;i++){
        //     int val = i*i;
        //     if(checkOptimal(val, 0, i)){
        //         sum += val;
        //     }
        // }

        // return sum;
    }
}