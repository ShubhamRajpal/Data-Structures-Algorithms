class Solution {

    private void solve(String tiles, StringBuilder cur, Set<String> res, boolean[] used) {
        res.add(cur.toString());

        for (int i = 0; i < tiles.length(); i++) {
            if (used[i] == true)
                continue;

            used[i] = true;
            cur.append(tiles.charAt(i));

            solve(tiles, cur, res, used);

            used[i] = false;
            cur.deleteCharAt(cur.length() - 1);
        }
    }

     private int solve(int[] freq) {
        

        int cnt  = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) continue;

            freq[i]--;
            cnt++;
            cnt  += solve(freq);
            freq[i]++;
        }

        return cnt;
    }


    public int numTilePossibilities(String tiles) {
        // int n = tiles.length();

        // Set<String> res = new HashSet<>();
        // boolean[] used = new boolean[n];
        // StringBuilder cur = new StringBuilder();

        // solve(tiles, cur, res, used);

        // return res.size() - 1;

        // Approach 2
        int[] freq = new int[26];
        for(char ch : tiles.toCharArray()){
            freq[ch - 'A']++;
        }

        int count = 0;
        return solve(freq);

        
    }
}