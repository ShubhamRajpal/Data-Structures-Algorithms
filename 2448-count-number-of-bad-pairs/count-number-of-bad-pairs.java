class Solution {
    public long countBadPairs(int[] nums) {
        
        int n = nums.length;
        Map<Integer, Integer> diffMap = new HashMap<>();
        // int[] arr = nums.clone();
        // for (int i = 0; i < n; i++) {
        //     arr[i] = nums[i] - i;
        // }

        long totalbadPairs = 0;
        for(int i = 0; i < n; i++){
            int diff = nums[i] - i;
            int goodPairs  = 0;
            if(diffMap.containsKey(diff)){
                goodPairs = diffMap.getOrDefault(diff, 0);
            }
            totalbadPairs += (i - goodPairs);
            diffMap.put(diff, diffMap.getOrDefault(diff, 0)+1);
        }

        return totalbadPairs;
        
    }
}