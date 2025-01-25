class Solution {
    private void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        // for(int i = 0; i < n; i++){
        //     while(true){
        //         int mini = nums[i];
        //         int ind = -1;
        //         for(int j = i+1; j < n; j++){
        //             if(Math.abs(nums[i]-nums[j]) <= limit){
        //                 if(nums[j] < mini){
        //                     mini = nums[j];
        //                     ind = j;
        //                 }
        //             }
        //         }

        //         if(ind != -1){
        //             swap(i, ind, nums);
        //         }else break;
        //     }
            
        // }

        // return nums;

        // Optimal
        int[] temp = nums.clone();
        Arrays.sort(temp);
        int groupNum = 0;
        Map<Integer, ArrayList<Integer>> groups = new HashMap<>();
        Map<Integer, Integer> numGroup = new HashMap<>();
        numGroup.put(temp[0], groupNum);
        groups.put(groupNum, new ArrayList<>());
        groups.get(groupNum).add(temp[0]);
        for(int i = 1; i < n; i++){
            if(Math.abs(temp[i]-temp[i-1]) > limit){
                groupNum += 1;
            }
            numGroup.put(temp[i], groupNum);
            if(groups.get(groupNum) == null) {
                groups.put(groupNum, new ArrayList<>());
                groups.get(groupNum).add(temp[i]);
            }
            else {
                groups.get(groupNum).add(temp[i]);
            }
        }

        System.out.print(groups);
        // System.out.print(groups);

        // Merge the groups
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            int group = numGroup.get(nums[i]);
            res[i] = groups.get(group).get(0);
            groups.get(group).remove(0);
            
        }

        return res;
    }
}