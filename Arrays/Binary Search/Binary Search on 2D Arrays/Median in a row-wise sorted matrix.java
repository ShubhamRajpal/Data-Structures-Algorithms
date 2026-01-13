/*
  Problem Link: https://www.geeksforgeeks.org/problems/median-in-a-row-wise-sorted-matrix1527/1  
*/

// T.C: O(n log m * log(maxVal - minVal)) , where n = no. of rows, m = no. of cols, maxVal = max value in matrix minVal = min value in matrix
// S.C: O(1)
class Solution {
    
    private int upperBound(int[] arr, int target){
        
        int m = arr.length;
        int low = 0, high = m-1;
        while(low <= high){
            
            int mid = (low + high) / 2;
            
            if(arr[mid] <= target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        
        return high;
    }
    
    public int countSmallerEqual(int mid, int n, int[][] mat){
        
        int cnt = 0;
        for(int i = 0; i < n; i++){
            cnt += upperBound(mat[i], mid) + 1;
        }
        
        return cnt;
    }
    
    
    public int median(int[][] mat) {
        
        int n = mat.length;
        int m = mat[0].length;
        
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        
        for(int i = 0; i < n; i++){
            low =  Math.min(mat[i][0], low);
            high =  Math.max(mat[i][m-1], high);
        }
        
        int medianCnt = (n * m) / 2;
        
        while(low <= high){
            
            int mid = (low + high) / 2;
            if(countSmallerEqual(mid, n, mat) > medianCnt){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        
        return low;
    }
}
