/*
  Problem Link: https://www.geeksforgeeks.org/problems/number-of-bst-from-array/1
*/

// Approach-1 (count no of BSTs for each element using DP(Tabulation))
// T.C: O(n^2 + n log n)
// S.C: O(n)
class Solution {

    public ArrayList<Integer> countBSTs(int[] arr) {
        // Code here
        int n = arr.length;
        Map<Integer, Integer> indices = new HashMap<>(); 
        for(int i = 0; i < n; i++){
            indices.put(arr[i], i);
        }
        
        Arrays.sort(arr);
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        
        
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            int idx = indices.get(arr[i]);
            int left = dp[i];
            int right = dp[n - i - 1];
            res[idx] = (left * right);
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < n; i++) ans.add(res[i]);
        return ans;
        
    }
}



// Approach-2 (using Catalan number formula C(n) = 2n! / (n! * (n+1)!))
// T.C: O(n log n)
// S.C: O(n)
class Solution {
    
    public ArrayList<Integer> getFactorial(int n){
          ArrayList<Integer> fact = new ArrayList<>(Collections.nCopies(n + 1, 1));
          for(int i = 1; i <= n; i++){
              fact.set(i, fact.get(i - 1) * i);
          }
          
          return fact;
    }
    
    public int getCatlanNumber(int num,  ArrayList<Integer> fact){
        return fact.get(2 * num) / (fact.get(num) * fact.get(num + 1));
    }

    public ArrayList<Integer> countBSTs(int[] arr) {
        
        int n = arr.length;
        Map<Integer, Integer> indices = new HashMap<>(); 
        for(int i = 0; i < n; i++){
            indices.put(arr[i], i);
        }
        
        Arrays.sort(arr);
        
        ArrayList<Integer> fact = getFactorial(2 * n);
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(n, 0));

        for(int i = 0; i < n; i++){
            int idx = indices.get(arr[i]);
            int left = i;
            int right = n - i - 1;
            ans.set(idx, getCatlanNumber(left, fact) * getCatlanNumber(right, fact));
        }
        
        return ans;
    }
}
