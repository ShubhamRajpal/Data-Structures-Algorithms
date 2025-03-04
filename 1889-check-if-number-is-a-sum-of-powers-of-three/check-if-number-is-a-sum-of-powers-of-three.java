class Solution {

    private int findLargestPower(int num){
        int x = 0, k = 1;
        while(k*3 <= num){
            k = k*3;
            if(k > num) return x;
            x++;
        }
        System.out.println(x);
        return x;
    }
    public boolean checkPowersOfThree(int n) {
        int temp = n;
        int sum = 0;
        while(temp != 0){
            // System.out.println(temp);
            int k = findLargestPower(temp);
            System.out.print(k);
            int num = (int)Math.pow(3,k);
            sum += num;
            temp = temp % num;
        }

        return (int)sum == n;
    }
}