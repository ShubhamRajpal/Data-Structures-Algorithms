/*
  Problem Link: https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/
  Similar Problem: (Leetcode - 3191) - "Minimum Operations to Make Binary Array Elements Equal to One I" (k = 3)
*/

//Approach-1 (Using auxiliary array to mark Flipped indices)
//T.C : O(n)
//S.C : O(n)
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int validFlipsFromPastWindow = 0;
        int cnt = 0;
        boolean[] isFlipped = new boolean[n];

        for (int i = 0; i < n; i++) {

            if (i >= k && isFlipped[i - k] == true) {
                validFlipsFromPastWindow--;
            }

            if (validFlipsFromPastWindow % 2 == nums[i]) {

                if (i + k > n) return -1;
                validFlipsFromPastWindow++;
                isFlipped[i] = true;
                cnt++;
            }
        }

        return cnt;
    }
}


//Approach-2 (Using same input to mark Flipped indices - We will be manipulating the input as well)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int validFlipsFromPastWindow = 0;
        int cnt = 0;

        for (int i = 0; i < n; i++) {

            if (i >= k && nums[i - k] == 2) {
                validFlipsFromPastWindow--;
            }

            if (validFlipsFromPastWindow % 2 == nums[i]) {

                if (i + k > n) return -1;
                validFlipsFromPastWindow++;
                nums[i] = 2;
                cnt++;
            }
        }

        return cnt;
    }

}


//Approach-3 (Using deque to mark Flipped indices)
//T.C : O(n)
//S.C : O(k)
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int validFlipsFromPastWindow = 0;
        int flips = 0;

        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {

            if (i >= k) {
                validFlipsFromPastWindow -= q.pollFirst();
            }

            if (validFlipsFromPastWindow % 2 == nums[i]) {

                if (i + k > n) return -1;
                validFlipsFromPastWindow++;
                flips++;
                q.addLast(1);
            }else{
                q.addLast(0);

            }
        }

        return flips;
    }

}
