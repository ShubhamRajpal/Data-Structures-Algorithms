class Solution {

    public String solve(StringBuilder sb, int n, Set<String> present) {
        if (sb.length() == n) {
            if (!present.contains(sb.toString())) {
                return sb.toString();
            }
            return "";
        }

        String ans = solve(sb.append('0'), n, present);
        sb.deleteCharAt(sb.length() - 1);
        if (ans.length() > 0)
            return ans;

        String ans2 = solve(sb.append('1'), n, present);
        sb.deleteCharAt(sb.length() - 1);
        return ans2;
    }

    public String findDifferentBinaryString(String[] nums) {
        // StringBuilder sb = new StringBuilder();
        // Set<String> present = new HashSet<>();
        // int n = nums.length;
        // for(String s : nums) present.add(s);

        // return solve(sb, n, present);

        // Better
        // Set<Integer> decimals = new HashSet<>();
        // int n = nums.length;
        // for(String s : nums){
        // decimals.add(Integer.parseInt(s,2));
        // }

        // for(int j = 0;j <= n; j++){
        // if(!decimals.contains(j)){
        // String temp = Integer.toBinaryString(j);

        // while(temp.length() < n) temp = '0'+ temp;
        // return temp;
        // }
        // }

        // return "";

        // Optimal
        String ans = "";
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            char str = nums[i].charAt(i);

            ans += str == '0' ? '1' : '0';
        }

        return ans;

    }
}