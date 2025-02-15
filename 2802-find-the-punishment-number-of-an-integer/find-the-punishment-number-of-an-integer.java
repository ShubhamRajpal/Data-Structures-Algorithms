class Solution {

    private boolean backtrack(int num, String square, int curSum) {
        if (square.length() == 0) {
            return curSum == num;
        }

        for (int i = 0; i < square.length(); i++) {
            if (backtrack(num, square.substring(i + 1), Integer.parseInt(square.substring(0, i + 1)) + curSum))
                return true;
        }

        return false;
    }

    public int punishmentNumber(int n) {
        
        int sum = 0;
        for(int i = 1; i <= n ; i++){
            if(backtrack(i, Integer.toString(i*i), 0)){
                sum += i*i;
            }
        }

        return sum;
    }
}