class Solution {

    private void solve(StringBuilder sb, char prev, int n, int k, List<String> ans){
        if(sb.length() == n){
            ans.add(sb.toString());
            return;
        }

        for(int i = 0; i < 3; i++){
            char curChar = (char)('a'+i);  
            // System.out.print(curChar+" ");
            if(prev == '\0' || curChar != prev){
                sb.append(curChar);
                // System.out.print(sb.toString());
                solve(sb, curChar, n, k, ans);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }


    public String getHappyString(int n, int k) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
         
        solve(sb, '\0', n, k, ans);
        System.out.print(ans);
        return ans.size() >= k ? ans.get(ans.size()-1 - (ans.size() - k)) : "";

    }
}