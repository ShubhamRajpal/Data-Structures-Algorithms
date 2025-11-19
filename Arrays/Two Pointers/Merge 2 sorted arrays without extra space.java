/*
  Problem Link: https://www.geeksforgeeks.org/problems/merge-two-sorted-arrays-1587115620/1
*/


//Approach-1 (Brute: using extra space for storing elements from both arrays in sorted order)
//T.C: O((n+m))
//S.C: O(n+m) 
class Solution {
    
    public void swap(int left, int right, int[] arr1, int[] arr2){
        if(arr1[left] > arr2[right]){
            int temp = arr1[left];
            arr1[left] = arr2[right];
            arr2[right] = temp;
        }    
    }
    
    public void mergeArrays(int a[], int b[]) {
        int n = a.length;
        int m = b.length;
        int c[] = new int[n+m];
        
        int i = 0, j = 0, k = 0;
        while(i < n && j < m){
            if(a[i] <= b[j]){
                c[k++] = a[i];
                i++;
            }else{
                c[k++] = b[j];
                j++;
            }
        }
        
        while(i < n){
            c[k++] = a[i];
            i++;
        }
        
        while(j < m){
            c[k++] = b[j];
            j++;
        }

      //Copy back the elements in respective arrays 
        for(int x = 0; x < (m + n); x++){
            if(x < n) {
                a[x] = c[x];
            }else{
                b[x-n] = c[x];
            }
        }
        
        
    }
}



//Approach-2 (Optimal 1: swap elements from arr1 and arr2 until arr1[i] < arr2[j])
//T.C: O(n+m)) + O(nlogn) + O(mlogm)
//S.C: O(1) 
class Solution {
    
    public void swap(int left, int right, int[] arr1, int[] arr2){
      
        int temp = arr1[left];
        arr1[left] = arr2[right];
        arr2[right] = temp;
    }
    
    public void mergeArrays(int a[], int b[]) {
        int n = a.length;
        int m = b.length;
        
        int i = n-1, j = 0;
        while(i >= 0 && j < m){
            if(a[i] > b[j]){
                swap(i, j, a, b);
            } else break;
            
            i--;
            j++;
        }
        
        Arrays.sort(a);
        Arrays.sort(b);
        
    }
}



//Approach-3 (Optimal 2: Using gap method in Shell sort)
//T.C: O((n+m) * log(n+m))
//S.C: O(1) 
class Solution {
    public void swap(int left, int right, int[] arr1, int[] arr2){
        if(arr1[left] > arr2[right]){
            int temp = arr1[left];
            arr1[left] = arr2[right];
            arr2[right] = temp;
        }    
    }
    
    
    public void mergeArrays(int a[], int b[]) {
        int n = a.length;
        int m = b.length;
        int len = (n+m);
        int gap = (len/2) + (len%2);
        
        while(gap > 0){
            int left = 0;
            int right = gap + left;
            
            while(right < len){
                if(left < n && right >= n){
                    swap(left, right - n, a, b);
                }else if(left >= n){
                    swap(left - n, right - n, b, b);
                }else if(left < n && right < n){
                    swap(left, right, a, a);
                }
                left++;
                right++;
            }
            
            if(gap == 1) break;
            gap = (gap/2) + (gap%2);
        }
        
    }
}
