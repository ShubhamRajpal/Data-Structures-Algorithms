class Solution {

    private boolean match(int m, String part, Stack<Character> st){
        Stack<Character> temp = new Stack<>();
        temp.addAll(st);

        for(int i = m-1; i >= 0; i--){
            if(temp.isEmpty() || temp.peek() != part.charAt(i)){
                return false;
            }

            temp.pop();
        }

        return true;
    }

    public String removeOccurrences(String s, String part) {
        // StringBuilder sb = new StringBuilder(s);
        // int m = part.length();
        // while(sb.indexOf(part) != -1){
        // int idx = sb.indexOf(part);
        // sb = sb.delete(idx, idx+m);
        // }

        // return sb.toString();

        // Approach 2 Using Stack
        int n = s.length();
        int m = part.length();
        Stack<Character> st = new Stack<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st.push(s.charAt(i));

            if (st.size() >= m && match(m, part, st)) {

                for (int j = 0; j < m; j++) {
                    st.pop();
                }
            }
        }

        while(!st.isEmpty()){
            res.append(st.pop());
        }

        res.reverse();
        return res.toString();

        // Approach 2 Using String as a Stack
        // int n = s.length();
        // StringBuilder res = new StringBuilder();
        // for (char c : s.toCharArray()) {
        // res.append(c);
        // if (res.length() >= part.length()) {
        // String substr = res.substring(res.length() - part.length());
        // if (substr.equals(part)) {
        // res = res.delete(res.length() - part.length(), res.length());
        // }
        // }

        // }

        // return res.toString();
    }
}