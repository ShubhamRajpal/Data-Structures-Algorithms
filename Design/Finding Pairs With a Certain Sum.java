//Approach (Using map and approach similar to Two Sum Problem)
//T.C : O(m+n) for FindSumPairs(), O(1) for add() and O(n) for count()
//S.C : O(m+n) for a[] and b[]

class FindSumPairs {
    Map<Integer, Integer> nums1Freq;
    Map<Integer, Integer> nums2Freq;
    int a[], b[];

    public FindSumPairs(int[] nums1, int[] nums2) {
        a = nums1;
        b = nums2;

        int n2 = b.length;

        nums2Freq = new HashMap<>();
        for (int i = 0; i < n2; i++) {
            nums2Freq.put(b[i], nums2Freq.getOrDefault(b[i], 0) + 1);
        }
    }

    public void add(int index, int val) {

        int curVal = b[index];
        nums2Freq.put(curVal, nums2Freq.getOrDefault(curVal, 0) - 1);
        if (nums2Freq.get(curVal) == 0) nums2Freq.remove(curVal);
        
        int newVal = curVal + val;
        b[index] = newVal;
        nums2Freq.put(newVal, nums2Freq.getOrDefault(newVal, 0) + 1);
    }

    public int count(int tot) {
        int n1 = a.length;
        int ans = 0;
        for (int num : a) {
            int rem = tot - num;

            if (nums2Freq.containsKey(rem)) {
                ans +=  nums2Freq.get(rem);
            }
        }

        return ans;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */
