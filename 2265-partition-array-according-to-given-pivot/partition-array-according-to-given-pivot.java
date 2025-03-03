class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        // int ans[] = new int[n];
        // List<Integer> lessThan = new ArrayList<>();
        // List<Integer> greaterThan = new ArrayList<>();
        // int cnt = 0;
        // for (int i = 0; i < n; i++) {
        //     if (nums[i] < pivot) {
        //         lessThan.add(nums[i]);
        //     } else if (nums[i] > pivot) {
        //         greaterThan.add(nums[i]);
        //     } else {
        //         cnt++;
        //     }
        // }

        // int k = 0;
        // for (int i = 0; i < lessThan.size(); i++) {
        //     ans[k++] = lessThan.get(i);
        // }

        // for (int i = 0; i < cnt; i++) {
        //     ans[k++] = pivot;
        // }

        // for (int i = 0; i < greaterThan.size(); i++) {
        //     ans[k++] = greaterThan.get(i);
        // }

        // return ans;

        // Better
        int[] res = new int[n];
        int less = 0, equal = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < pivot) {
                less++;
            } else if (nums[i] == pivot) {
                equal++;
            }
        }

        int greater = less + equal;
        int lessCnt = 0, equalCnt = less;
        for (int i = 0; i < n; i++) {
            if (nums[i] < pivot) {
                res[lessCnt++] = nums[i];
            } else if (nums[i] == pivot) {
                res[equalCnt++] = pivot;
            } else {
                res[greater++] = nums[i];
            }
        }

        return res;

    }
}