class Solution {
    public String clearDigits(String s) {
        StringBuilder sb = new StringBuilder(s);
        int idx = 0, n = sb.length();
        while(idx < sb.length()){
            if(Character.isDigit(sb.charAt(idx))){
                sb.deleteCharAt(idx);

                if(idx > 0){
                    sb.deleteCharAt(idx-1);
                    idx--;
                }

                if(sb.length() == 0) return "";
            }
            else idx++;
        }

        return sb.toString();
    }
}