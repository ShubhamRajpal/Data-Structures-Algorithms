class Solution {

    public boolean isPrime(int num){

        if(num == 1)  return false;

        for (int i = 2; i*i <= num; i++) {
            if(num % i == 0) return false;
        }

        return true;
    }
    public int[] closestPrimes(int left, int right) {

        List<Integer> primes = new ArrayList<>();
        for(int i = left; i <= right; i++){
            if(isPrime(i)){
                if(primes.size() != 0 && (i - primes.get(primes.size() - 1) <= 2)){
                    return new int[]{primes.get(primes.size()-1),i};
                }
                primes.add(i);
            }
        }

        int[] res = new int[2];
        int mini = Integer.MAX_VALUE;
        Arrays.fill(res, -1);
        for (int i = 1; i < primes.size(); i++) {

            int diff = primes.get(i) - primes.get(i-1);
            if (diff < mini) {
                mini = diff;
                res[0] = primes.get(i-1);
                res[1] = primes.get(i);
            }
        
        }

        return res;
    }
}