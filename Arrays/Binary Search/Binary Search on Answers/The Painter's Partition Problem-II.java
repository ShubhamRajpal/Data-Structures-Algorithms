/*
  Problem Link:   https://www.geeksforgeeks.org/problems/the-painters-partition-problem1535/1
  Similar Qn : LC 410. Split Array Largest Sum - https://leetcode.com/problems/split-array-largest-sum/description/
*/

// Approach  (binary search on answers min(max))
// T.C: O(n log (max - min))
// S.C: O(1)
class Solution {
    
    public boolean canPaint(int mid, int[] arr, int k){
        int cntPainters = 1;
        int totalTime = 0;
        for(int i  = 0; i < arr.length; i++){
            if(arr[i] + totalTime <= mid){
                totalTime += arr[i];
            }else{
                cntPainters++;
                totalTime = arr[i];
            }
        }
        
        return cntPainters <= k;
    }
    public int minTime(int[] arr, int k) {
        // code here
        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();
        
        while(low <= high){
            int mid = (low + high) / 2;
            
            if(canPaint(mid, arr, k)){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        
        return low;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    }
}
