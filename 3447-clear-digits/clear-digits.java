class Solution {
    public String clearDigits(String s) {
        // StringBuilder sb = new StringBuilder(s);
        // int idx = 0; 
        // while(idx < sb.length()){
        //     if(Character.isDigit(sb.charAt(idx))){
        //         sb.deleteCharAt(idx);

        //         if(idx > 0){
        //             sb.deleteCharAt(idx-1);
        //             idx--;
        //         }

        //         if(sb.length() == 0) return "";
        //     }
        //     else idx++;
        // }

        // return sb.toString();

        // Using Stack TC -> O(N) SC -> O(N)
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(char ch : s.toCharArray()){
            if(Character.isDigit(ch) && !st.isEmpty()){
                st.pop();
            }else{
                st.push(ch);
            }
        }

        while(!st.isEmpty()){
            sb.append(st.pop());
        }

        return sb.reverse().toString();
    }
}