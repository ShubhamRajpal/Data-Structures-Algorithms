/*
  Problem Link: https://leetcode.com/problems/count-elements-with-maximum-frequency/
*/

//Approach-1 (Using 2 Pass)
//T.C : O(n)
//S.C : O(1)
public class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] arr = new int[101];

        int maxFreq = 0;

        for (int num : nums) {
            arr[num]++;
            maxFreq = Math.max(maxFreq, arr[num]);
        }

        final int finalMaxFreq = maxFreq; //why final keyword ? To access inside lambda
        return (int) Arrays.stream(arr).filter(count -> count == finalMaxFreq).count() * finalMaxFreq;
    }
}



//Approach-2 (Using 1 Pass)
//T.C : O(n)
//S.C : O(1)
public class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] arr = new int[101];

        int maxFreq = 0;
        int total = 0;

        for (int num : nums) {
            arr[num]++;

            int freq = arr[num];

            if (freq > maxFreq) {
                maxFreq = freq;
                total = freq;
            } else if (freq == maxFreq) {
                total += freq;
            }
        }

        return total;
    }
}
