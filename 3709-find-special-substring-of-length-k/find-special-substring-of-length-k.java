class Solution {
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length();
        char str[] = s.toCharArray();
        int cnt = 0;

        for (int i = 0; i <= n - k; i++) {
            char ch = str[i];
            boolean flag = true;
            for (int j = i; j < i + k; j++) {
                if(str[j] != ch){
                    flag = false;
                    break;
                }
            }

            if(flag){
                if((i > 0 && str[i-1] == ch) || (i+k < n && str[i+k] == str[i])) continue;
                return true;
            }
        }

        return false;
    }
}