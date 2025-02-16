class Solution {
    public boolean hasSpecialSubstring(String s, int k) {
        // int n = s.length();
        // char str[] = s.toCharArray();

        // for (int i = 0; i <= n - k; i++) {
        //     char ch = str[i];
        //     boolean flag = true;
        //     for (int j = i; j < i + k; j++) {
        //         if (str[j] != ch) {
        //             flag = false;
        //             break;
        //         }
        //     }

        //     if (flag) {
        //         if ((i > 0 && str[i - 1] == ch) || (i + k < n && str[i + k] == str[i]))
        //             continue;
        //         return true;
        //     }
        // }

        // return false;

        // Sliding Window
        int low = 0, high = 0, n = s.length();
        char str[] = s.toCharArray();
        int[] freq = new int[26];

        while (high < n) {
            freq[str[high] - 'a']++;

            while(high - low + 1 > k){
                freq[str[low] - 'a']--;
                low++;
            }

            if (high - low + 1 == k) {
                if (freq[str[low] - 'a'] == k && (low == 0 || str[low - 1] != str[low]) &&
                        (high == n-1 || str[high] != str[high + 1])) {
                    return true;
                }
            }

            high++;
        }

        return false;
    }
}