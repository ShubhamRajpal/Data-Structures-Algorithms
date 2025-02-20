class Solution {

    public String solve(StringBuilder sb , int n, Set<String> present){
        if(sb.length() == n) {
            if(!present.contains(sb.toString())){
                return sb.toString();
            }
            return "";
        }

        String ans = solve(sb.append('0'), n, present);
        sb.deleteCharAt(sb.length()-1);
        if(ans.length() > 0) return ans;

        String ans2 =  solve(sb.append('1'), n, present);
        sb.deleteCharAt(sb.length()-1);
        return ans2;
    }


    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        Set<String> present = new HashSet<>();
        int n = nums.length;
        for(String s : nums) present.add(s);

        return solve(sb, n, present);


    }
}