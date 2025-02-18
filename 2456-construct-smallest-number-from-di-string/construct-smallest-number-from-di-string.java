class Solution {
    private void reversed(int i, int j, char[] ans){
        while(i < j){
            char temp = ans[i];
            ans[i] = ans[j];
            ans[j] = temp;
            i++;
            j--;
        }
    }


    public String smallestNumber(String pattern) {
        char[] pat = pattern.toCharArray();
        int n = pattern.length();
        char[] ans = new char[n+1];

        for(int i = 0; i < n+1; i++){
            ans[i] = (char)((i+1) + '0');
        }

        int i = 0;
        while(i < n){
            if(pat[i] == 'D'){
                int j = i;
                while(j < n && pat[j] != 'I'){
                    j++;
                }

                reversed(i,j,ans);
                i = j;
            }else {
                i++;
            }


        }

        return String.valueOf(ans);
    }
}