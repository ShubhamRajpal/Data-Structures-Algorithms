/*
  Problem Link: https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/description/
*/

//Approach-1 (Straight forward)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];

        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);

        int result = word.length();
        int cumulativeDeleted = 0;

        for (int i = 0; i < 26; i++) {
            int del = cumulativeDeleted;
            for (int j = 25; j > i; j--) {
                if (freq[j] - freq[i] <= k) {
                    break;
                }

                del += freq[j] - freq[i] - k;
            }

            result = Math.min(result, del);
            cumulativeDeleted += freq[i];
        }

        return result;
    }
}


//Approach-2 (slight optimisation of above approach)
//T.C : O(n) because other iterations are on a constant sized array
//S.C : O(1) - 26 sized array
class Solution {
    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];

        for(char ch : word.toCharArray()) {
            freq[ch-'a']++;
        }

        Arrays.sort(freq);

        int result = Integer.MAX_VALUE;
        int deletedTillNow = 0;

        for(int i = 0; i < 26; i++) {
            int minFreq = freq[i];
            int temp = deletedTillNow; //temp taken to find deletion for j = 25 to j > i

            for(int j = 25; j > i; j--) {
                if(freq[j] - freq[i] <= k) 
                    break;
                
                temp += freq[j] - minFreq - k;
            }

            result = Math.min(result, temp);
            deletedTillNow += minFreq;
        }

        return result;
    }
}
