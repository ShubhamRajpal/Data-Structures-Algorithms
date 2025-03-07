/*
    Problem Link: https://leetcode.com/problems/closest-prime-numbers-in-range/?envType=daily-question&envId=2025-03-07
*/


// Approach 1 (Using Sieve Of Eratosthenes)
//T.C : O(Rlog(log(R)) + (R-L))
//S.C : O(R)
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

// Approach 2 (Using Twin Prime Pairs for early return)
//T.C : O((R-L) * sqrt(R))
//S.C : O(R-L)
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
        // Find if Twin prime paira exists in the given range
        for(int i = left; i <= right; i++){
            if(isPrime(i)){
                if(primes.size() != 0 && (i - primes.get(primes.size() - 1) <= 2)){
                    return new int[]{primes.get(primes.size()-1),i};
                }
                primes.add(i);
            }
        }

         // Find the closest prime pair if no twin prime pair was found
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
