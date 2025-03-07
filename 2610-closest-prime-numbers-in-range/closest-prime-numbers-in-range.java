class Solution {

    public int[] sieveHelper(int right){

        int[] isPrime = new int[right+1];
        for (int i = 2; i <= right; i++) {
            isPrime[i] = 1;
        }

        for (int i = 2; i*i <= right; i++) {
            if (isPrime[i] == 1) {
                for (int j = i * i; j <= right; j += i) {
                    isPrime[j] = 0;
                }
            }
        }

        return isPrime;
    }
    public int[] closestPrimes(int left, int right) {
        int[] isPrime = sieveHelper(right);

        List<Integer> prime = new ArrayList<>();
        for(int i = left; i <= right; i++){
            if(isPrime[i] == 1){
                prime.add(i);
            }
        }


        int mini = Integer.MAX_VALUE;
        int[] res = new int[2];
        Arrays.fill(res, -1);
        int num1 = -1;
        for (int i = 1; i < prime.size(); i++) {

            int diff = prime.get(i) - prime.get(i-1);
            if (diff < mini) {
                mini = diff;
                res[0] = prime.get(i-1);
                res[1] = prime.get(i);
            }
        
        }

        return res;
    }
}