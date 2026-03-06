/*
  Problem Link: https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
*/

// Approach (binary search on answers min(max))
// T.C: O(n log (max - min))
// S.C: O(1)
class Solution {
    
    public boolean canAllocate(int midPages, int[] arr, int k){
        int n = arr.length;
        int studentPagesCnt = 0, studentCnt = 1;
        
        for(int i = 0; i < n; i++){
            if(studentPagesCnt + arr[i] <= midPages){
                studentPagesCnt += arr[i];
            }else{
                studentCnt++;
                studentPagesCnt = arr[i];
            }
        }
        
        return studentCnt <= k;
    }
    
    
    public int findPages(int[] arr, int k) {
        // code here
        int n = arr.length;
        
        if(n < k) return -1;
        
        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();
        
        while(low <= high){
            
            int mid = (low + high) / 2;
            
            if(canAllocate(mid, arr, k)){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        
        return low;
        
    }
}
