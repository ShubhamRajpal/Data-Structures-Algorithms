/*
  Problem Link: https://www.geeksforgeeks.org/problems/aggressive-cows/0
*/


// Approach (binary search on answers max(min))
// T.C: O(n log(maxD - minD)) maxD & minD = maximum and miniumum positions of stalls
// S.C: O(1)
class Solution {
    
    public boolean canAssignCows(int minDis, int[] stalls, int k){
        int n = stalls.length;
        int cowCnt = 0, lastCowPos = -1;
        for(int i = 0; i < n; i++){
            if(lastCowPos == -1 || ((stalls[i] - lastCowPos) >= minDis)){
                cowCnt++;
                lastCowPos = stalls[i];
            }
        }
        
        return cowCnt >= k;
        
    }
    
    public int aggressiveCows(int[] stalls, int k) {
        // code here
        int n = stalls.length;
        Arrays.sort(stalls);
        
        int minD = 1;
        int maxD = stalls[n-1];
        
        int low = minD, high = maxD;
        while(low <= high){
            
            int mid = (low + high) / 2;
            
            if(canAssignCows(mid, stalls, k)){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        
        return high;
        
    }
}
