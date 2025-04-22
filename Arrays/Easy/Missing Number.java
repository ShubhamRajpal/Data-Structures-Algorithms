/*
  Problem Link: https://leetcode.com/problems/missing-number/description
*/
//Approach-1(Try all numbers in [0,n])
//T.C- O(N*N)
//S.C- O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int missNo = -1;
        for(int i=0;i<=nums.length;i++){
            boolean isPresent = false;
            for(int j=0;j<nums.length;j++){
                if(nums[j]==i){
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                missNo = i;
                break;
            }
        }

        return missNo;
    }
}

//Approach-2(Using Map)
//T.C- O(N)
//S.C- O(N)
class Solution {
    public static int findMissingNoBetter(int[] nums) {

		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
		}

		for (int i = 0; i <= nums.length; i++) {
			if (!map.containsKey(i)) {
				return i;
			}
		}

		return -1;
	}

    public int missingNumber(int[] nums) {
        return findMissingNoBetter(nums);
    }
}

//Approach-3(Find Sum of all given numbers and Subtract it from sum of all natural numbers in [0,n])
//T.C- O(N)
//S.C- O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int sumOfAllNums = 0;
        int n = nums.length;
        sumOfAllNums = n * (n + 1) / 2;

        int arrSum = 0;
        for (int i : nums) {
            arrSum += i;
        }

        return sumOfAllNums - arrSum;
    }
}


//Approach-4(Find xor of all given numbers in array and all natural numbers in [0,n])
//T.C- O(N) Slighly better than sum Approach since it avoids Integer overflow for n >= 10^5. At max for loop goes till n.
//S.C- O(1)
class Solution {
    public int missingNumber(int[] nums) {
        int xor1 = 0, xor2 = 0;

        for(int i = 0;i < nums.length;i++){
            xor1 ^= nums[i];
            xor2 ^= (i+1);

        }

        return xor1^xor2;
    }
}
