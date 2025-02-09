class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> diagMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int dis = i - j;
                diagMap.putIfAbsent(dis, new ArrayList<>());
                diagMap.get(dis).add(grid[i][j]);
            }
        }

        // Iterate throught each diagonal to sort them
        for (Map.Entry<Integer, List<Integer>> entry : diagMap.entrySet()) {
            List<Integer> temp = entry.getValue();
            if (entry.getKey() >= 0) {
                temp.sort(Collections.reverseOrder());
            } else {
                Collections.sort(temp);
            }
        }

        // Insert the sorted diagonals into grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int dis = i - j;
                grid[i][j] = diagMap.get(dis).remove(0);
            }
        }

        return grid;
    }
}