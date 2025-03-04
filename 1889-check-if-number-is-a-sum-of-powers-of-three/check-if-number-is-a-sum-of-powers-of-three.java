class Solution {

    private int findLargestPower(int num){
        int x = 0, k = 1;
        while(k*3 <= num){
            k = k*3;
            if(k > num) return x;
            x++;
        }
        return x;
    }

    private boolean solve(int num, int p){
        if(num == 0) return true;

        if(Math.pow(3, p) > num) return false;

        return solve(num - (int)(Math.pow(3,p)), p+1) || solve(num, p+1);

    }
    public boolean checkPowersOfThree(int n) {
        // int temp = n;
        // int sum = 0;
        // while(temp != 0){
        //     int k = findLargestPower(temp);
        //     int num = (int)Math.pow(3,k);
        //     sum += num;
        //     temp = temp % num;
        // }

        // return (int)sum == n;

        // Approach 2 using Ternary Representation
        // int temp = n;
        // while(temp != 0){
        //     int rem = temp % 3;
        //     if(rem == 2) return false;
        //     temp = temp/3;
        // }

        // return true;

        // Appraoch 3 Using Backtracking
        return solve(n, 0);
    }
}