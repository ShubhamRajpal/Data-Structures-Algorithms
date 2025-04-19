/*
  Problem Link: https://leetcode.com/problems/maximum-candies-allocated-to-k-children/description/?envType=daily-question&envId=2025-03-14
*/

// Approach 1 (brute using Linear Search)
//T.C. - O(N * maxCandies)
// S.C. - O(1)
class Solution {

    public static boolean canBeDistributed(int[] candies, int maxQ, long children){
        
        long totalChildCount = 0;
        for(int i : candies){
            totalChildCount += (i/maxQ);
        }

        if(totalChildCount <= children){
            return true;
        }
        return false;
    }

    public int maximumCandies(int[] candies, long k) {
        
        int maxQty = -1;
        long sum = 0;
        for(int i : candies){
            maxQty = Math.max(maxQty,i);
            sum += i;
        }

        if(sum < k) return 0;

        for(int i = 1; i <= maxQty; i++){
            if(canBeDistributed(candies,i,k)){
                return i;
            }
        }
        return -1; 
    }
}

// Approach 2 (Using Binary Search)
// T.C. - O(N * log(maxCandies))
// S.C. - O(1)
class Solution {

    public static boolean canBeDistributed(int[] candies, int mid, long k){
        
        long totalChildCount = 0;
        for(int i = 0; i < candies.length; i++){
            totalChildCount += (candies[i]/mid);
        }
        
        if(totalChildCount >= k){
            return true;
        }
        return false;
    }

    public int maximumCandies(int[] candies, long k) {
        
        int maxCandies = Arrays.stream(candies).max().getAsInt();

        int low = 1, high = maxCandies;
        while(low <= high){
            int mid = low + (high-low)/2;

            if(canBeDistributed(candies,mid,k)){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        return high;

    }
}
