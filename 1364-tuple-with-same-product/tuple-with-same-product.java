class Solution {
    public int tupleSameProduct(int[] nums) {

        // Brute
        // int n = nums.length;
        // int tupleCnt = 0;
        // Arrays.sort(nums);
        // for (int i = 0; i < n; i++) {
        // for (int j = n-1; j>i ; j--) {
        // for (int k = i+1; k < j; k++) {
        // for (int l = j-1; l > k; l--) {
        // // if (i != k && i != l && j != k && j != l) {
        // int p1 = nums[i] * nums[j];
        // int p2 = nums[k] * nums[l];

        // if (p1 == p2) {
        // tupleCnt++;
        // }
        // // }
        // }
        // }
        // }
        // }
        // return tupleCnt*8;

        // Better
        // int n = nums.length;
        // Arrays.sort(nums);
        // int tuple = 0;
        // for (int i = 0; i < n; i++) {
        // for (int j = n - 1; j > i; j--) {
        // int p1 = nums[i] * nums[j];
        // Set<Integer> st = new HashSet<>();

        // for (int k = i + 1; k < j; k++) {
        // if (p1 % nums[k] == 0) {
        // int l = p1 / nums[k];
        // if (st.contains(l)) {
        // tuple++;
        // }
        // st.add(nums[k]);
        // }
        // }
        // }
        // }

        // return tuple * 8;

        // Optimal
        int tuple = 0;
        int n = nums.length;
        Map<Integer, Integer> prodMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int p = nums[i] * nums[j];
                prodMap.put(p, prodMap.getOrDefault(p, 0) + 1);
            }
        }

        // Find if a product occurs more than once
        for(Map.Entry<Integer, Integer> entry : prodMap.entrySet()){
            if(entry.getValue() > 1){
                int val = entry.getValue();
                tuple += (val * (val-1))/2;
            }
        }

        return tuple*8;
    }
}