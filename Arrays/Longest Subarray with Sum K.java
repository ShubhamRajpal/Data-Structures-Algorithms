/*
  Problem Link: https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
*/

//Approach-1(Using 3 loops)
//T.C - O(N^3)
//T.C - O(1)
class Solution {
    public int longestSubarray(int[] arr, int k) {
        
        int n = arr.length;
        int len = 0;
        for(int i = 0;i < n; i++){
            for(int j = i; j < n; j++){
                int sum = 0;
                for(int K = i; K <= j; K++){
                    sum += arr[K];
                }
                if(sum == k) len = Math.max(len, j - i + 1);
            }
        }
        
        return len;
    }
}


//Approach-2(Using 2 loops)
//T.C - O(N^2)
//T.C - O(1)
class Solution {
    public int longestSubarray(int[] arr, int k) {
        
        int n = arr.length;
        int len = 0;
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = i; j < n; j++){
                sum += arr[j];
                if(sum == k) len = Math.max(len, j - i + 1);
            }
        }
        
        return len;
    }
}


//Approach-3(Using Map - Optimal approach for arr containing both +ve and -ve )
//T.C - O(N)
//T.C - O(N)
class Solution {
    public int longestSubarray(int[] arr, int k) {
        
        int n = arr.length;
        int len = 0;
        int prefixSum = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i = 0; i < n; i++){
            prefixSum += arr[i];
            
            if(prefixSum == k){
                len = Math.max(len, i+1);
            }
            
            int rem = prefixSum - k;
            if(mp.containsKey(rem)){
                len = Math.max(len, i - mp.get(rem));
            }
            
            if(!mp.containsKey(prefixSum)){
                mp.put(prefixSum, i);
            }
        }
        
        return len;
    }
}


//Approach-4(Using 2 pointers - Best approach for arr containing only +ve numbers)
//T.C - O(N)
//T.C - O(1)
class Solution {
    public int longestSubarray(int[] arr, int k) {
        
       int n = arr.length; // size of the array.

        int left = 0, right = 0; // 2 pointers
        long sum = arr[0];
        int maxLen = 0;
        while (right < n) {

            // if sum > k, reduce the subarray from left
            // until sum becomes less or equal to k:
            while (left <= right && sum > k) {
                sum -= arr[left];
                left++;
            }

            // if sum = k, update the maxLen i.e. answer:
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Move forward thw right pointer:
            right++;
            if (right < n) sum += arr[right];
        }

        return maxLen;
    }
}
