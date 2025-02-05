class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2))
            return true;
        int n = s2.length();

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        // int cnt = 0;
        // for (int i = 0; i < n; i++) {
        //     if (ch1[i] == ch2[i])
        //         continue;

        //     else {
        //         int ind = -1;
        //         if (cnt == 0) {
        //             for (int j = i + 1; j < n; j++) {
        //                 if (ch1[i] == ch2[j] && ch1[j] == ch2[i]) {
        //                     ind = j;
        //                     break;
        //                 }
        //             }
        //             if (ind == -1) return false;
        //             char temp = ch2[i];
        //             ch2[i] = ch2[ind];
        //             ch2[ind] = temp;
        //             cnt++;
        //         }else return false;
        //     }

        // }

        // return true;

        // Better Approach
        int[] freqS1 = new int[26];
        int[] freqS2 = new int[26];
        int cntDiff = 0;
        for(int i = 0; i < n; i++){
            if(ch1[i] != ch2[i]){
                cntDiff++;

                if(cntDiff > 2) return false;
            }

            freqS1[ch1[i] - 'a']++;
            freqS2[ch2[i] - 'a']++;
            
        }

        return Arrays.equals(freqS1,freqS2); 
    }
}