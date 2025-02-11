class Solution {
    public String removeOccurrences(String s, String part) {
        // StringBuilder sb = new StringBuilder(s);
        // int m = part.length();
        // while(sb.indexOf(part) != -1){
        //     int idx = sb.indexOf(part);
        //     sb = sb.delete(idx, idx+m);
        // }

        // return sb.toString();

        // Approach 2 Using Stack
        int n = s.length();
        StringBuilder res = new StringBuilder();
        for(char c : s.toCharArray()){
            res.append(c);
            if(res.length() >= part.length()){
                String substr = res.substring(res.length() - part.length());
                if(substr.equals(part)){
                    res = res.delete(res.length() - part.length(), res.length());
                }
            }
            
        }

        return res.toString();
    }
}