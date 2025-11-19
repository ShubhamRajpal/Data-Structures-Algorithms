
/*
  Problem Link: https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
*/

// Approach (using Merge Sort)
// T.C: O(nlogn)
// S.C: O(n) for temp array
class Solution {
    
    static int merge(int low, int mid, int high, int[] arr){
        int left = low;
        int right = mid + 1;
        int cnt = 0;
        List<Integer> temp = new ArrayList<>();
        
        while(left <= mid && right <= high){
            
            if(arr[left] <= arr[right]){
                temp.add(arr[left]);
                left++;
            }else{
                // add no of elements in first array to the pairs count (size of first array = mid+1)
                cnt += (mid - left + 1);
                temp.add(arr[right]);
                right++;
            }
        }
        
        while(left <= mid){
            temp.add(arr[left]);
            left++;
        }
        
        while(right <= high){
            temp.add(arr[right]);
            right++;
        }
        
        
        for(int i = low; i <= high; i++){
            arr[i] = temp.get(i - low);
        }
        
        return cnt;
    }
    
    static int mergeSort(int low, int high, int[] arr){
        if(low >= high) return 0;
        
        int cnt = 0;
        int mid = (low + high) / 2;
        
        cnt += mergeSort(low, mid, arr);
        cnt += mergeSort(mid + 1, high, arr);
        cnt += merge(low, mid, high, arr);
        
        return cnt;
        
    }
    static int inversionCount(int arr[]) {
        
        int n = arr.length;
        return mergeSort(0, n-1, arr);
        
    }
}
