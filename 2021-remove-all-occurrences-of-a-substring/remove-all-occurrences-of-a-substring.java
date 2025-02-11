class Solution {
    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        int m = part.length();
        while(sb.indexOf(part) != -1){
            int idx = sb.indexOf(part);
            sb = sb.delete(idx, idx+m);
        }

        return sb.toString();
    }
}