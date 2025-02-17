class Solution {

    private void solve(String tiles, StringBuilder cur, Set<String> res, boolean[] used){
        res.add(cur.toString());

        for(int i =0;i< tiles.length(); i++){
            if(used[i] == true) continue;
            
            used[i] = true;
            cur.append(tiles.charAt(i));

            solve(tiles, cur, res, used);

            used[i] = false;
            cur.deleteCharAt(cur.length() - 1);
        }
    }


    public int numTilePossibilities(String tiles) {
        int n = tiles.length();

        Set<String> res = new HashSet<>();
        boolean[] used = new boolean[n];
        StringBuilder cur = new StringBuilder();

        solve(tiles, cur, res, used);

        return res.size() - 1;
    }
}