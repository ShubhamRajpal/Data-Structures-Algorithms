class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
       int n = nums.length;
       int ans[] = new int[n];
       List<Integer> lessThan = new ArrayList<>();
       List<Integer> greaterThan = new ArrayList<>();
       int cnt = 0;
       for(int i = 0;i < n;i++){
            if(nums[i] < pivot){
                lessThan.add(nums[i]);
            }else if(nums[i] > pivot){
                greaterThan.add(nums[i]);
            }else{
                cnt++;
            }
       }

        int k = 0;
       for(int i = 0;i < lessThan.size(); i++){
            ans[k++] = lessThan.get(i);
       }

       for(int i = 0;i < cnt; i++){
            ans[k++] = pivot;
       }
       

       for(int i = 0;i < greaterThan.size(); i++){
            ans[k++] = greaterThan.get(i);
       }

       return ans;


    }
}