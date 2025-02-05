class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2))
            return true;
        int n = s2.length();
        // int[] freqS2 = new int[n];
        // for(char ch : s1.toCharArray()){
        // freqS2[ch-'a']++;
        // }

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (ch1[i] == ch2[i])
                continue;

            else {
                int ind = -1;
                if (cnt == 0) {
                    for (int j = i + 1; j < n; j++) {
                        if (ch1[i] == ch2[j] && ch1[j] == ch2[i]) {
                            ind = j;
                            break;
                        }
                    }
                    if (ind == -1) return false;
                    char temp = ch2[i];
                    ch2[i] = ch2[ind];
                    ch2[ind] = temp;
                    cnt++;
                }else return false;
            }

        }

        return true;
    }
}