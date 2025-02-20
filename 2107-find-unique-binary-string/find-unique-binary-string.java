class Solution {

    public String solve(String sb , int n, Set<String> present){
        if(sb.length() == n) {
            if(!present.contains(sb)){
                return sb;
            }
            return "";
        }

        String ans = solve(sb+'0', n, present);
        if(ans.length() > 0) return ans;

        String ans2 =  solve(sb+'1', n, present);
        return ans2;
    }


    public String findDifferentBinaryString(String[] nums) {
        Set<String> present = new HashSet<>();
        int n = nums.length;
        for(String s : nums) present.add(s);

        return solve("", n, present);


    }
}