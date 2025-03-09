class Solution {
    public int numberOfAlternatingGroups(int[] colors) {
        int n = colors.length;
        int cnt = 0;
        // for (int i = 0; i < n; i++) {
        //     for (int j = i; j < i + 3; j++) {
        //         if (j == i + 2) {
        //             if (colors[j % n] == colors[(j - 2) % n] && colors[(j - 1) % n] != colors[j % n]) {
        //                 cnt++;
        //             }
        //         }
        //     }
        // }

        // return cnt;

        // Optimal using sliding window
        for (int j = 0; j < n; j++) {
            if (colors[j % n] == colors[(j + 2) % n] && colors[(j + 1) % n] != colors[j % n]) {
                cnt++;
            }
                
        }

        return cnt;
    }
}