class Solution {

    public void solve(StringBuilder sb , int n, Set<String> present, List<String> ans){
        if(sb.length() == n) {
            ans.add(sb.toString());
            return;
        }

        sb.append('0');
        solve(sb, n, present, ans);
        sb.deleteCharAt(sb.length()-1);

        sb.append('1');
        solve(sb, n, present, ans);
        sb.deleteCharAt(sb.length()-1);

    }


    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        Set<String> present = new HashSet<>();
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        for(String s : nums) present.add(s);

        solve(sb, n, present, ans);
        for(String str : ans){
            if(!present.contains(str)) return str;
        }

        return "";


    }
}