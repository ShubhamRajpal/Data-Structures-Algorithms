/*
  Problem Link: https://leetcode.com/problems/subarray-sum-equals-k/description/
*/

// Approach-1 (using 3 loops)
//T.C- O(N^3)
//S.C- O(1)
class Solution {
    public int subarraySum(int[] nums, int k) {
        
    int n = nums.length;
    int count = 0, sum = 0;
    int s = k;
    for (int i = 0; i < n; i++) {
	for (int j = i; j < n; j++) {
	    sum = 0;
	    for (int k1 = i; k1 <= j; k1++) {
		sum += nums[k1];
	    }

	    if (sum == s) {
		count++;
	    }
	}
    }
    return count;
    }
}


// Approach-2 (using 2 loops)
//T.C- O(N^2)
//S.C- O(1)
class Solution {
    public int subarraySum(int[] nums, int k) {
        
    int n = nums.length;
    int count = 0, sum = 0;
    int s = k;
    for (int i = 0; i < n; i++) {
      sum = 0;
      for (int j = i; j < n; j++) {
      	sum += nums[j];
	if (sum == s) {
		count++;
	}
      }
    }
	return count;
    }
}


// Approach-3 (using Map & prefixSum)
//T.C- O(N)
//S.C- O(N)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        int cnt = 0;
        int prefixSum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];

            int rem = prefixSum - k;
            if (mp.containsKey(rem)) {
                cnt += mp.get(rem);
            }

            mp.put(prefixSum, mp.getOrDefault(prefixSum, 0) + 1);

        }

        return cnt;
    }
}
